package com.tekion.cricket.util;

import com.tekion.cricket.bean.Match;
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

  public void findResult(Match match) {
    if (match.getScoreBoardInnings1().getTeamScore() > match.getScoreBoardInnings2().getTeamScore()) {
      System.out.println(getTeamOneWonString(match.getScoreBoardInnings1(), match.getScoreBoardInnings2()));
    } else if (match.getScoreBoardInnings1().getTeamScore() < match.getScoreBoardInnings2().getTeamScore()) {
      System.out.println(getTeamTwoWonString(match.getScoreBoardInnings2()));
    } else {
      System.out.println("!!Draw!!");
    }
  }

  private String getTeamOneWonString(ScoreBoard scoreBoardInnings1, ScoreBoard scoreBoardInnings2){
    return String.format(TEAM_ONE_WON_STRING, scoreBoardInnings1.getTeamName(), scoreBoardInnings1.getTeamScore() - scoreBoardInnings2.getTeamScore());
  }

  private String getTeamTwoWonString(ScoreBoard scoreBoardInnings2){
    return String.format(TEAM_TWO_WON_STRING, scoreBoardInnings2.getTeamName(), (Constants.NUMBER_WICKETS-scoreBoardInnings2.getWicketFallen()));
  }
}
