package com.tekion.cricket.service;

import com.tekion.cricket.CricketApplication;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.util.CricketUtility;
import com.tekion.cricket.util.InningsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.logging.Logger;

@SpringBootApplication
public class CricketService {
  private static Random random = new Random();
  private static Scanner scanner = new Scanner(System.in);
  private static Logger logger = Logger.getLogger(CricketService.class.getName());
  private static final String TOSS_WON_STRING = "Team %s Won the Toss";

  public static void main(String[] args){
    SpringApplication.run(CricketApplication.class, args);
    Match match = new Match();
    TeamService teamService = new TeamService();
    logger.info("Enter Team 1 Details : ");
    TeamDetails teamOneDetails = teamService.initialiseTeam();
    match.setTeamOneName(teamOneDetails.getTeamName());

    logger.info("Enter Player 2 Name : ");
    TeamDetails teamTwoDetails = teamService.initialiseTeam();
    match.setTeamTwoName(teamTwoDetails.getTeamName());

    match.setNumberOfOvers(getNumberOfOvers());

    match.setTossWinner(toss(teamOneDetails, teamTwoDetails));
    logger.info(getTossWonString(match.getTossWinner().getTeamName()));

    chooseBatOrField(teamOneDetails, teamTwoDetails, match);

    ScoreBoard scoreBoardInnings1 = InningsUtil.playFirstInning(match);
    match.setScoreBoardInnings1(scoreBoardInnings1);

    ScoreBoard scoreBoardInnings2 = InningsUtil.playSecondInning(match);
    match.setScoreBoardInnings2(scoreBoardInnings2);

    CricketUtility.findResult(match);
  }


  private static int getNumberOfOvers(){
    int overs;
    while (true) {
      logger.info("No. of Overs in the Match : ");
      overs = scanner.nextInt();
      if (overs > 0) {
        break;
      }
      logger.info("Please Enter a valid number greater than 0");
    }
    return overs;
  }

  private static TeamDetails toss(TeamDetails teamOneDetails, TeamDetails teamTwoDetails){
    int tossResult = random.nextInt(2);
    if(tossResult == 0){
      return teamOneDetails;
    }
    else{
      return teamTwoDetails;
    }
  }

  private static void chooseBatOrField(TeamDetails teamOneDetails, TeamDetails teamtwoDetails, Match match){
    int battingChoice;
    while (true) {
      System.out.println("Press 0 for Bat First \nPress 1 for Field First");
      battingChoice = scanner.nextInt();
      if (battingChoice == 0 || battingChoice == 1) {
        break;
      } else {
        System.out.println("!! Please Choose a valid input !!");
      }
    }
    if(battingChoice == 0){
      match.setBattingFirstTeam(match.getTossWinner());
      if(match.getTossWinner().equals(teamOneDetails)){
        match.setBowlingFirstTeam(teamtwoDetails);
      }
      else{
        match.setBowlingFirstTeam(teamOneDetails);
      }
    }
    else{
      match.setBowlingFirstTeam(match.getTossWinner());
      if(match.getTossWinner().equals(teamOneDetails)){
        match.setBattingFirstTeam(teamtwoDetails);
      }
      else{
        match.setBattingFirstTeam(teamOneDetails);
      }
    }
  }

  private static String getTossWonString(String tossWinner){
    return String.format(TOSS_WON_STRING, tossWinner);
  }
}
