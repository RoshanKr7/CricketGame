package com.tekion.cricket;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.repo.IBattingScoreCardRepository;
import com.tekion.cricket.repo.IBowlingScoreCardRepository;
import com.tekion.cricket.repo.IMatchSummaryRepository;
import com.tekion.cricket.service.*;
import com.tekion.cricket.util.CricketUtil;
import com.tekion.cricket.util.InningsUtil;
import com.tekion.cricket.util.TossUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.logging.Logger;


@RequiredArgsConstructor
@SpringBootApplication
public class CricketApplication implements ApplicationRunner {
	private static Match match = new Match();
    private static final String TOSS_WON_STRING = "Team %s Won the Toss";
    private BattingScoreCardService battingScoreCardService= new BattingScoreCardService();
    private BowlingScoreCardService bowlingScoreCardService = new BowlingScoreCardService();
    private MatchService matchService = new MatchService();
    private static Logger logger = Logger.getLogger(CricketApplication.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private final IMatchSummaryRepository matchSummaryRepository;
    private final IBattingScoreCardRepository battingScoreCardRepository;
    private final IBowlingScoreCardRepository bowlingScoreCardRepository;

	public static void main(String[] args) {
        TeamService teamService = new TeamService();
        TeamDetails teamOneDetails;
        TeamDetails teamTwoDetails;
        System.out.println("Do you want to Enter team by file or Manually?");
        String teamDetailEntryWay = getWayToEnterTeamDetails();
        if(teamDetailEntryWay.equalsIgnoreCase("file")){
            System.out.println("\nEnter Team 1 Details File Name: ");
            teamOneDetails = teamService.initialiseTeamByFile();
            System.out.println("\nEnter Team 2 Details File name : ");
            teamTwoDetails = teamService.initialiseTeamByFile();
        }
        else{
            System.out.println("\nEnter Team 1 Details : ");
            teamOneDetails = teamService.initialiseTeam();
            System.out.println("\nEnter Team 2 Details : ");
            teamTwoDetails = teamService.initialiseTeam();
        }

        match.setTeamOneName(teamOneDetails.getTeamName());
        match.setTeamTwoName(teamTwoDetails.getTeamName());

        match.setNumberOfOvers(getNumberOfOvers());

        match.setTossWinnerName(TossUtil.toss(teamOneDetails, teamTwoDetails));
        System.out.println(getTossWonString(match.getTossWinnerName()));

        TossUtil.chooseBatOrField(teamOneDetails, teamTwoDetails, match);

        ScoreBoard scoreBoardInnings1 = InningsUtil.playFirstInning(match);
        match.setScoreBoardInnings1(scoreBoardInnings1);

        ScoreBoard scoreBoardInnings2 = InningsUtil.playSecondInning(match);
        match.setScoreBoardInnings2(scoreBoardInnings2);

        MatchSummaryService matchSummaryService = new MatchSummaryService();
        match.setMatchSummary(matchSummaryService.finaliseMatchSummary(match));
        CricketUtil.findResult(match);
        SpringApplication.run(CricketApplication.class, args);
	}

    @Override
	public void run(ApplicationArguments args) throws Exception {
        matchSummaryRepository.save(match.getMatchSummary());
        matchService.addMatchId(match);
        battingScoreCardService.addMatchIdToAllBatters(match);
        bowlingScoreCardService.addMatchIdToAllBowler(match);
        battingScoreCardRepository.saveAll(match.getScoreBoardInnings1().getBattingScoreCard());
        battingScoreCardRepository.saveAll(match.getScoreBoardInnings2().getBattingScoreCard());
        bowlingScoreCardRepository.saveAll(match.getScoreBoardInnings1().getBowlingScoreCard());
        bowlingScoreCardRepository.saveAll(match.getScoreBoardInnings2().getBowlingScoreCard());
	}

    private static String getWayToEnterTeamDetails(){
        String teamDetailEntryWay;
        while(true){
            teamDetailEntryWay = scanner.next();
            if(teamDetailEntryWay.equalsIgnoreCase("file")){
                return teamDetailEntryWay;
            }
            else if(teamDetailEntryWay.equalsIgnoreCase("manually")){
                return teamDetailEntryWay;
            }
            else{
                logger.info("\nEnter a valid Option");
            }
        }
    }

    private static int getNumberOfOvers(){
        int overs;
        while (true) {
            logger.info("\nNo. of Overs in the Match : ");
            overs = scanner.nextInt();
            if (overs > 0) {
                break;
            }
            logger.info("Please Enter a valid number greater than 0");
        }
        return overs;
    }

    private static String getTossWonString(String tossWinner){
        return String.format(TOSS_WON_STRING, tossWinner);
    }

}
