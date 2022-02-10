package com.tekion.cricket.util;

import com.tekion.cricket.bean.ScoreBoard;

import java.util.List;
import java.util.Random;

public class CricketUtility {
  private static Random random = new Random();
  private static final String TEAM_ONE_WON_STRING = "Team %s has won the match by %d Runs";
  private static final String TEAM_TWO_WON_STRING = "Team %s has won the match by %d Wickets and %d Balls";

  public static int scoreGenerator(List<Integer> scoreChances, int MaximumNumber) {
    int randomNumber = random.nextInt(MaximumNumber+1);
    int score = 0;
    while (scoreChances.get(score) < randomNumber) {
      score++;
    }
    return score;
  }

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void result(ScoreBoard scoreBoardTeam1, ScoreBoard scoreBoardTeam2) {
    if (scoreBoardTeam1.getTeamScore() > scoreBoardTeam2.getTeamScore()) {
      System.out.println(getTeamOneWonString(scoreBoardTeam1, scoreBoardTeam2));
    } else if (scoreBoardTeam1.getTeamScore() < scoreBoardTeam2.getTeamScore()) {
      System.out.println(getTeamTwoWonString(scoreBoardTeam2));
    } else {
      System.out.println("!!Draw!!");
    }
  }

  private static String getTeamOneWonString(ScoreBoard scoreBoardTeam1, ScoreBoard scoreBoardTeam2){
    return String.format(TEAM_ONE_WON_STRING, scoreBoardTeam1.getTeamName(), scoreBoardTeam1.getTeamScore() - scoreBoardTeam2.getTeamScore());
  }

  private static String getTeamTwoWonString(ScoreBoard scoreBoardTeam2){
    return String.format(TEAM_TWO_WON_STRING, scoreBoardTeam2.getTeamName(), (10-scoreBoardTeam2.getWicketFallen()), scoreBoardTeam2.getNumberOfBalls() - scoreBoardTeam2.getBallsThrown());
  }
}
