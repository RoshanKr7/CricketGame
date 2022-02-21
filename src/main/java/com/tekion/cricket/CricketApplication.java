package com.tekion.cricket;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.repo.BattingScoreCardRepository;
import com.tekion.cricket.repo.BowlingScoreCardRepository;
import com.tekion.cricket.repo.MatchSummaryRepository;
import com.tekion.cricket.service.BattingScoreCardService;
import com.tekion.cricket.service.BowlingScoreCardService;
import com.tekion.cricket.service.MatchSummaryService;
import com.tekion.cricket.service.TeamService;
import com.tekion.cricket.util.CricketUtility;
import com.tekion.cricket.util.InningsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.tekion.cricket.service.CricketService.*;


@SpringBootApplication
public class CricketApplication implements ApplicationRunner {
	private static Match match = new Match();
    private static final String TOSS_WON_STRING = "Team %s Won the Toss";
    private BattingScoreCardService battingScoreCardService = new BattingScoreCardService();
    private BowlingScoreCardService bowlingScoreCardService = new BowlingScoreCardService();
    @Autowired
    private MatchSummaryRepository matchSummaryRepository;
    @Autowired
    private BattingScoreCardRepository battingScoreCardRepository;
    @Autowired
    private BowlingScoreCardRepository bowlingScoreCardRepository;

	public static void main(String[] args) {
        TeamService teamService = new TeamService();
        System.out.println("\nEnter Team 1 Details File Name: ");
    //    TeamDetails teamOneDetails = teamService.initialiseTeam();
        TeamDetails teamOneDetails = teamService.initialiseTeamByFile();
        match.setTeamOneName(teamOneDetails.getTeamName());
        System.out.println("\nEnter Team 2 Details File name : ");
    //    TeamDetails teamTwoDetails = teamService.initialiseTeam();
        TeamDetails teamTwoDetails = teamService.initialiseTeamByFile();
        match.setTeamTwoName(teamTwoDetails.getTeamName());

        match.setNumberOfOvers(getNumberOfOvers());

        match.setTossWinner(toss(teamOneDetails, teamTwoDetails));
        System.out.println(getTossWonString(match.getTossWinner().getTeamName()));

        chooseBatOrField(teamOneDetails, teamTwoDetails, match);

        ScoreBoard scoreBoardInnings1 = InningsUtil.playFirstInning(match);
        match.setScoreBoardInnings1(scoreBoardInnings1);

        ScoreBoard scoreBoardInnings2 = InningsUtil.playSecondInning(match);
        match.setScoreBoardInnings2(scoreBoardInnings2);

        MatchSummaryService matchSummaryService = new MatchSummaryService();
        match.setMatchSummary(matchSummaryService.finaliseMatchSummary(match));
        CricketUtility.findResult(match);
        for (BowlingScoreCard bowlingScoreCard : match.getScoreBoardInnings2().getBowlingScoreCard()){
            System.out.println(bowlingScoreCard);
        }
        SpringApplication.run(CricketApplication.class, args);
	}

    @Override
	public void run(ApplicationArguments args) throws Exception {
        matchSummaryRepository.save(match.getMatchSummary());
        battingScoreCardService.addMatchIdToAllBatters(match);
        bowlingScoreCardService.addMatchIdToAllBowler(match);
        battingScoreCardRepository.saveAll(match.getScoreBoardInnings1().getBattingScoreCard());
        battingScoreCardRepository.saveAll(match.getScoreBoardInnings2().getBattingScoreCard());
        bowlingScoreCardRepository.saveAll(match.getScoreBoardInnings1().getBowlingScoreCard());
        bowlingScoreCardRepository.saveAll(match.getScoreBoardInnings2().getBowlingScoreCard());
	}

    private static String getTossWonString(String tossWinner){
        return String.format(TOSS_WON_STRING, tossWinner);
    }

}
