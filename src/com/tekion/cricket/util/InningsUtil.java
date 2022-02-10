package com.tekion.cricket.util;

import com.tekion.cricket.bean.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

public class InningsUtil {
  private static final String INNINGS_START_STRING = "Team %s is going to start their innings";
  private static final String PARTNERSHIP_BROKEN_STRING =  "Wicket Number %1$d has Fallen \n Partnership for Wicket %1$d is %2$d";
  private static final String AFTER_EACH_OVER_STRING = "After Over %d : %d/%d";
  private static final String INNINGS_OVER_STRING = "Team %s Innings Over \nFinal Score : %d/%d\n\n";

  private static List<Integer> scoreChances;

  public static ScoreBoard startFirstInning(String teamName, int numberOfBalls) {
    ScoreBoard scoreBoard = new ScoreBoard();

    scoreBoard.setTeamName(teamName);
    scoreBoard.setNumberOfBalls(numberOfBalls);
    System.out.println(String.format(INNINGS_START_STRING, teamName));
    CricketUtility.wait(3000);
    scoreChances = makeScoreChances();
    while (scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()) {
      updateScoreBoardForEachBall(scoreBoard);
    }
    System.out.println(getInningsOverString(scoreBoard));
    CricketUtility.wait(2000);
    return scoreBoard;
  }

  public static ScoreBoard startSecondInning(
      String teamName, ScoreBoard scoreBoardTeam1, int numberOfBalls) throws InterruptedException {
    ScoreBoard scoreBoard = new ScoreBoard();

    scoreBoard.setTeamName(teamName);
    scoreBoard.setNumberOfBalls(numberOfBalls);
    System.out.println(String.format(INNINGS_START_STRING, teamName));
    CricketUtility.wait(3000);
    scoreChances = makeScoreChances();

    while (scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()) {
      updateScoreBoardForEachBall(scoreBoard);
      if (scoreBoard.getTeamScore() > scoreBoardTeam1.getTeamScore()) {
          break;
      }
    }

    System.out.println(getInningsOverString(scoreBoard));
    CricketUtility.wait(2000);
    return scoreBoard;
  }

  private static List<Integer> makeScoreChances(){
    List<Integer> scoreChances = new ArrayList<>();
    scoreChances.add(20);
    scoreChances.add(45);
    scoreChances.add(65);
    scoreChances.add(72);
    scoreChances.add(87);
    scoreChances.add(88);
    scoreChances.add(98);
    scoreChances.add(105);
    return scoreChances;
  }

  private static void updateScoreBoardForEachBall(ScoreBoard scoreBoard){
    updateBallsThrown(scoreBoard);
    int score;
    score = CricketUtility.scoreGenerator(scoreChances, scoreChances.get(scoreChances.size() - 1));
    if (score == Constants.CONSTANT_FOR_OUT) {
      addWicketFallen(scoreBoard);
      System.out.println(getPartnershipBrokenString(scoreBoard));
      CricketUtility.wait(2000);
    } else {
      updatePartnerships(scoreBoard, score);
      updateTeamScore(scoreBoard, score);
    }
    if ((scoreBoard.getBallsThrown() % Constants.NUMBER_OF_BALL_IN_OVER) == 0) {
      System.out.println(getAfterEachOverString(scoreBoard));
      CricketUtility.wait(1000);
    }
  }

  private static void addWicketFallen(ScoreBoard scoreBoard){
    scoreBoard.setWicketFallen(scoreBoard.getWicketFallen() + 1);
  }

  private static void updatePartnerships(ScoreBoard scoreBoard, int score) {
    if(scoreBoard.partnerships.size() == scoreBoard.getWicketFallen()){
      scoreBoard.partnerships.add(0);
    }
    else{
      scoreBoard.partnerships.set(scoreBoard.getWicketFallen(), scoreBoard.partnerships.get(scoreBoard.getWicketFallen()) + score);
    }

  }

  public static void updateBallsThrown(ScoreBoard scoreBoard) {
    scoreBoard.setBallsThrown(scoreBoard.getBallsThrown() + 1);
  }

  public static void updateTeamScore(ScoreBoard scoreBoard, int score) {
    scoreBoard.setTeamScore(scoreBoard.getTeamScore() + score);
  }

  private static int currentPartnerships(ScoreBoard scoreBoard){
    if(scoreBoard.partnerships.size() == scoreBoard.getWicketFallen()){
      scoreBoard.partnerships.add(0);
    }
    return scoreBoard.getPartnerships().get(scoreBoard.getWicketFallen()-1);
  }

  private static String getPartnershipBrokenString(ScoreBoard scoreBoard){
    return String.format(PARTNERSHIP_BROKEN_STRING, scoreBoard.getWicketFallen(), currentPartnerships(scoreBoard));
  }

  private static String getAfterEachOverString(ScoreBoard scoreBoard){
    return String.format(AFTER_EACH_OVER_STRING, (scoreBoard.getBallsThrown() / Constants.NUMBER_OF_BALL_IN_OVER), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }

  private static String getInningsOverString(ScoreBoard scoreBoard){
    return String.format(INNINGS_OVER_STRING, scoreBoard.getTeamName(), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }
}
