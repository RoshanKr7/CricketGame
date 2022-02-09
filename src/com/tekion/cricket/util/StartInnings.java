package com.tekion.cricket.util;

import com.tekion.cricket.repo.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

public class StartInnings {
  public static ScoreBoard startFirstInning(String teamName, int numberOfBalls) {
    ScoreBoard scoreBoard = new ScoreBoard();
    List<Integer> scoreChances = new ArrayList<>();
    scoreChances.add(20);
    scoreChances.add(45);
    scoreChances.add(65);
    scoreChances.add(72);
    scoreChances.add(87);
    scoreChances.add(88);
    scoreChances.add(98);
    scoreChances.add(105);

    scoreBoard.setTeamName(teamName);
    scoreBoard.setNumberOfBalls(numberOfBalls);
    System.out.println("Team " + teamName + " is going to start their innings");
    CricketUtility.wait(3000);
    scoreBoard.setPartnerships();
    while (scoreBoard.getWicketFallen() < Constants.WICKETS
        && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()) {
      scoreBoard.updateBallsThrown();
      int score;
      score = CricketUtility.scoreGenerator(scoreChances);
      if (score == Constants.OUT) {
        scoreBoard.updateWicketFallen();
        System.out.println("Wicket Number " + scoreBoard.getWicketFallen() + " has Fallen");
        System.out.println(
            "Partnership for Wicket "
                + scoreBoard.getWicketFallen()
                + " is "
                + scoreBoard.getPartnerships());
        CricketUtility.wait(2000);
        scoreBoard.setPartnerships();
      } else {
        scoreBoard.updatePartnerships(score);
        scoreBoard.updateTeamScore(score);
      }
      if ((scoreBoard.getBallsThrown() % 6) == 0) {
        System.out.println(
            "After Over "
                + (scoreBoard.getBallsThrown() / 6)
                + " : "
                + scoreBoard.getTeamScore()
                + "/"
                + scoreBoard.getWicketFallen());
        CricketUtility.wait(1000);
      }
    }
    System.out.println(
        "Team "
            + teamName
            + " Innings Over \n"
            + "Final Score : "
            + scoreBoard.getTeamScore()
            + "/"
            + scoreBoard.getWicketFallen());
    CricketUtility.wait(2000);
    return scoreBoard;
  }

  public static ScoreBoard startSecondInning(
      String teamName, ScoreBoard scoreBoardTeam1, int numberOfBalls) throws InterruptedException {
    ScoreBoard scoreBoard = new ScoreBoard();
    List<Integer> scoreChances = new ArrayList<>();
    scoreChances.add(20);
    scoreChances.add(45);
    scoreChances.add(65);
    scoreChances.add(72);
    scoreChances.add(87);
    scoreChances.add(88);
    scoreChances.add(98);
    scoreChances.add(105);

    scoreBoard.setTeamName(teamName);
    scoreBoard.setNumberOfBalls(numberOfBalls);
    System.out.println("Team " + teamName + " is going to start their innings");
    CricketUtility.wait(3000);
    scoreBoard.setPartnerships();
    while (scoreBoard.getWicketFallen() < Constants.WICKETS
        && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()) {
      scoreBoard.updateBallsThrown();
      int score;
      score = CricketUtility.scoreGenerator(scoreChances);
      if (score == Constants.OUT) {
        scoreBoard.updateWicketFallen();
        System.out.println("Wicket Number " + scoreBoard.getWicketFallen() + " has Fallen");
        System.out.println(
            "Partnership for Wicket "
                + scoreBoard.getWicketFallen()
                + " is "
                + scoreBoard.getPartnerships());
        CricketUtility.wait(2000);
        scoreBoard.setPartnerships();
      } else {
        scoreBoard.updatePartnerships(score);
        scoreBoard.updateTeamScore(score);
        if (scoreBoard.getTeamScore() > scoreBoardTeam1.getTeamScore()) {
          break;
        }
      }
      if ((scoreBoard.getBallsThrown() % Constants.BALL_IN_OVER) == 0) {
        System.out.println(
            "After Over "
                + (scoreBoard.getBallsThrown() / Constants.BALL_IN_OVER)
                + " : "
                + scoreBoard.getTeamScore()
                + "/"
                + scoreBoard.getWicketFallen());
        CricketUtility.wait(1000);
      }
    }
    System.out.println(
        "Team "
            + teamName
            + " Innings Over \n"
            + "Final Score : "
            + scoreBoard.getTeamScore()
            + "/"
            + scoreBoard.getWicketFallen());
    CricketUtility.wait(2000);
    return scoreBoard;
  }
}
