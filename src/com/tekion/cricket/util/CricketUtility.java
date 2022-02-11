package com.tekion.cricket.util;

import com.tekion.cricket.bean.ScoreBoard;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass

public class CricketUtility {
  private static Random random = new Random();
  private static final String TEAM_ONE_WON_STRING = "Team %s has won the match by %d Runs";
  private static final String TEAM_TWO_WON_STRING = "Team %s has won the match by %d Wickets";

  public int scoreGenerator(List<Integer> scoreChances, int maximumNumber) {
    int randomNumber = random.nextInt(maximumNumber+1);
    int score = 0;
    while (scoreChances.get(score) < randomNumber) {
      score++;
    }
    return score;
  }

  public void waitForMilliSec(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public void findResult(ScoreBoard scoreBoardTeam1, ScoreBoard scoreBoardTeam2) {
    if (scoreBoardTeam1.getTeamScore() > scoreBoardTeam2.getTeamScore()) {
      System.out.println(getTeamOneWonString(scoreBoardTeam1, scoreBoardTeam2));
    } else if (scoreBoardTeam1.getTeamScore() < scoreBoardTeam2.getTeamScore()) {
      System.out.println(getTeamTwoWonString(scoreBoardTeam2));
    } else {
      System.out.println("!!Draw!!");
    }
  }

  private String getTeamOneWonString(ScoreBoard scoreBoardTeam1, ScoreBoard scoreBoardTeam2){
    return String.format(TEAM_ONE_WON_STRING, scoreBoardTeam1.getTeamName(), scoreBoardTeam1.getTeamScore() - scoreBoardTeam2.getTeamScore());
  }

  private String getTeamTwoWonString(ScoreBoard scoreBoardTeam2){
    return String.format(TEAM_TWO_WON_STRING, scoreBoardTeam2.getTeamName(), (Constants.NUMBER_WICKETS-scoreBoardTeam2.getWicketFallen()));
  }
}
