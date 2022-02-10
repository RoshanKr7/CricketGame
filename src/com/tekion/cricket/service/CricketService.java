package com.tekion.cricket.service;

import com.tekion.cricket.bean.ScoreBoard;
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
    List<String> playerName = new ArrayList<>();

    logger.info("Enter Player 1 Name : ");
    playerName.add(getTeamName());
    logger.info("Enter Player 2 Name : ");
    playerName.add(getTeamName());

    int overs = getNumberOfOvers();

    int battingFirstTeam = toss(playerName);

    if (battingFirstTeam == 1) {
      Collections.swap(playerName, 0, 1);
    }

    ScoreBoard scoreBoardTeam1 =
        InningsUtil.startFirstInning(playerName.get(0), overs * Constants.NUMBER_OF_BALL_IN_OVER);

    ScoreBoard scoreBoardTeam2 = InningsUtil.startSecondInning(
            playerName.get(1), scoreBoardTeam1, overs * Constants.NUMBER_OF_BALL_IN_OVER);

    CricketUtility.result(scoreBoardTeam1, scoreBoardTeam2);
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

  private static int toss(List<String> playerName) {
    int tossResult = random.nextInt(2);
    int battingChoice = 0;
    logger.info(getTossWonString(playerName, tossResult));
    while (true) {
      System.out.println("Press 0 for Bat First \nPress 1 for Field First");
      battingChoice = scanner.nextInt();
      if (battingChoice == 0 || battingChoice == 1) {
        break;
      } else {
        System.out.println("!! Please Choose a valid input !!");
      }
    }
    return (battingChoice ^ tossResult);
  }

  private static String getTossWonString(List<String> playerName, int tossResult){
    return String.format(TOSS_WON_STRING, playerName.get(tossResult));
  }
}
