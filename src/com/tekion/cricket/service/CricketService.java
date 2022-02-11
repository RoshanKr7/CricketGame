package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.util.Constants;
import com.tekion.cricket.util.CricketUtility;
import com.tekion.cricket.util.InningsUtil;

import java.util.*;
import java.util.logging.Logger;

class CricketService {
  private static Random random = new Random();
  private static Scanner scanner = new Scanner(System.in);
  private static Logger logger = Logger.getLogger(CricketService.class.getName());
  private static final String TOSS_WON_STRING = "Team %s Won the Toss";

  public static void main(String[] args) throws InterruptedException {
    Match match = new Match();
    logger.info("Enter Team 1 Details : ");
    TeamDetails teamOneDetails = initialiseTeam();
    match.setTeamOneName(teamOneDetails.getTeamName());

    logger.info("Enter Player 2 Name : ");
    TeamDetails teamTwoDetails = initialiseTeam();
    match.setTeamTwoName(teamTwoDetails.getTeamName());

    match.setNumberOfOvers(getNumberOfOvers());

    match.setTossWinner(toss(teamOneDetails, teamTwoDetails));
    logger.info(getTossWonString(match.getTossWinner().getTeamName()));

    chooseBatOrField(teamOneDetails, teamTwoDetails, match);

    ScoreBoard scoreBoardTeam1 = InningsUtil.startFirstInning(match);

    ScoreBoard scoreBoardTeam2 = InningsUtil.startSecondInning(match, scoreBoardTeam1);

    CricketUtility.result(scoreBoardTeam1, scoreBoardTeam2);
  }

  private static TeamDetails initialiseTeam(){
    TeamDetails teamDetails = new TeamDetails();
    System.out.print("Enter team name : ");
    teamDetails.setTeamName(getTeamName());
    int numberOfPlayer = 0;
    List <PlayerDetails> playerDetailsList = new ArrayList<>();
    while(numberOfPlayer <= Constants.NUMBER_WICKETS){
      PlayerDetails playerDetails = new PlayerDetails();
      System.out.println("Enter Player " + (numberOfPlayer+1) + " Detail : ");
      System.out.print("Name : ");
      playerDetails.setPlayerName(scanner.next());
      System.out.print("\nType (Bat/Ball) : ");
      playerDetails.setPlayerType(scanner.next());
      System.out.print("\nBatting Rating (Out of 10) : ");
      playerDetails.setBattingRating(scanner.nextInt());
      System.out.print("\nBowling Rating (Out of 10) : ");
      playerDetails.setBowlingRating(scanner.nextInt());
      playerDetailsList.add(playerDetails);
      numberOfPlayer++;
    }
    teamDetails.setPlayersDetails(playerDetailsList);
    return teamDetails;
  }


  private static String getTeamName(){
    String teamName;
    while (true) {
      teamName = scanner.next();
      if (teamName.equals("")) {
        logger.info("Please Enter something in Name");
      }
      else{
        break;
      }
    }
    return teamName;
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
