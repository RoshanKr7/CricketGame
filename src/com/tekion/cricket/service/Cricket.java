package com.tekion.cricket.service;

import com.tekion.cricket.repo.ScoreBoard;
import com.tekion.cricket.util.Constants;
import com.tekion.cricket.util.CricketUtility;
import com.tekion.cricket.util.StartInnings;

import java.util.*;
import java.util.logging.Logger;

class Cricket {
  private static Random random = new Random();
  private static Scanner scanner = new Scanner(System.in);

  private static int toss(List<String> playerName) {
    Logger logger = Logger.getLogger(Cricket.class.getName());
    int tossResult = random.nextInt(2);
    int choice = 0;
    logger.info("Team " + playerName.get(tossResult) + " Won the Toss");
    while (true) {
      System.out.println("Press 0 for Bat First \nPress 1 for Field First");
      choice = scanner.nextInt();
      if (choice == 0 || choice == 1) {
        break;
      } else {
        System.out.println("!! Please Choose a valid input !!");
      }
    }
    return (choice ^ tossResult);
  }

  public static void main(String[] args) throws InterruptedException {
    Logger logger = Logger.getLogger(Cricket.class.getName());
    List<String> playerName = new ArrayList<>();

    logger.info("Enter Player 1 Name : ");
    playerName.add(scanner.next());
    logger.info("Enter Player 2 Name : ");
    playerName.add(scanner.next());

    int battingFirstTeam = toss(playerName);

    if (battingFirstTeam == 1) {
      Collections.swap(playerName, 0, 1);
    }

    int overs;
    while (true) {
      logger.info("No. of Overs in the Match : ");
      overs = scanner.nextInt();
      if (overs > 0) {
        break;
      }
      logger.info("Please Enter a valid number greater than 0");
    }

    ScoreBoard scoreBoardTeam1 =
        StartInnings.startFirstInning(playerName.get(0), overs * Constants.BALL_IN_OVER);

    System.out.println();

    ScoreBoard scoreBoardTeam2 =
        StartInnings.startSecondInning(
            playerName.get(1), scoreBoardTeam1, overs * Constants.BALL_IN_OVER);

    System.out.println();

    CricketUtility.result(scoreBoardTeam1, scoreBoardTeam2);
  }
}
