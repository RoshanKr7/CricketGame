package com.tekion.cricket.util;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.Over;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;

import java.util.ArrayList;
import java.util.List;

public class InningsUtil {
  private static final String INNINGS_START_STRING = "Team %s is going to start their innings";
  private static final String PARTNERSHIP_BROKEN_STRING =  "Wicket Number %1$d has Fallen \n Partnership for Wicket %1$d is %2$d";
  private static final String AFTER_EACH_OVER_STRING = "After Over %d : %d/%d";
  private static final String INNINGS_OVER_STRING = "Team %s Innings Over \nFinal Score : %d/%d\n\n";

  private static List<Integer> scoreChances;

  public static ScoreBoard startFirstInning(Match match) {
    ScoreBoard scoreBoard = new ScoreBoard();
    Over over = new Over();
    TeamDetails battingTeam = match.getBattingFirstTeam();
    initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));
    scoreChances = makeScoreChances();

    startNewPartnership(scoreBoard);
    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(scoreBoard, over);
    }

    System.out.println(getInningsOverString(scoreBoard));
    return scoreBoard;
  }

  public static ScoreBoard startSecondInning(Match match, ScoreBoard scoreBoardTeam1){
    ScoreBoard scoreBoard = new ScoreBoard();
    Over over = new Over();
    TeamDetails battingTeam = match.getBowlingFirstTeam();
    initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));
    scoreChances = makeScoreChances();

    startNewPartnership(scoreBoard);
    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(scoreBoard, over);
      if (scoreBoard.getTeamScore() > scoreBoardTeam1.getTeamScore()) {
        break;
      }
    }

    System.out.println(getInningsOverString(scoreBoard));
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

  private static void updateScoreBoardForEachBall(ScoreBoard scoreBoard, Over over){
    Integer score;
    score = CricketUtility.scoreGenerator(scoreChances, scoreChances.get(scoreChances.size() - 1));

    if (score == Constants.CONSTANT_FOR_OUT) {
      addWicketFallen(scoreBoard);
      System.out.println(getPartnershipBrokenString(scoreBoard));
      startNewPartnership(scoreBoard);
      over.balls.put(over.balls.size(), "W");
      CricketUtility.waitForMilliSec(2000);
    } else {
      over.balls.put(over.balls.size(), score.toString());
      AddScoreToPartnerships(scoreBoard, score);
      updateTeamScore(scoreBoard, score);
    }
    if(over.balls.size() == Constants.NUMBER_OF_BALL_IN_OVER){
      incrementOver(scoreBoard, over);
      over.balls.clear();
      System.out.println(getAfterEachOverString(scoreBoard));
    }
  }

  private static void initialiseScoreboard(ScoreBoard scoreBoard, String teamName){
    scoreBoard.setTeamName(teamName);
  }

  private static void addWicketFallen(ScoreBoard scoreBoard){
    scoreBoard.setWicketFallen(scoreBoard.getWicketFallen() + 1);
  }

  private static void startNewPartnership(ScoreBoard scoreBoard){
    scoreBoard.partnerships.add(0);
  }

  private static void AddScoreToPartnerships(ScoreBoard scoreBoard, int score) {
    scoreBoard.partnerships.set(scoreBoard.getWicketFallen(), scoreBoard.partnerships.get(scoreBoard.getWicketFallen()) + score);
  }

  private static void incrementOver(ScoreBoard scoreBoard,Over over){
    scoreBoard.overs.add(over);
    scoreBoard.setOversThrown(scoreBoard.getOversThrown() + 1);
  }

  public static void updateTeamScore(ScoreBoard scoreBoard, int score) {
    scoreBoard.setTeamScore(scoreBoard.getTeamScore() + score);
  }

  private static int currentPartnerships(ScoreBoard scoreBoard){
    return scoreBoard.getPartnerships().get(scoreBoard.getWicketFallen()-1);
  }

  private static String getPartnershipBrokenString(ScoreBoard scoreBoard){
    return String.format(PARTNERSHIP_BROKEN_STRING, scoreBoard.getWicketFallen(), currentPartnerships(scoreBoard));
  }

  private static String getAfterEachOverString(ScoreBoard scoreBoard){
    return String.format(AFTER_EACH_OVER_STRING, scoreBoard.getOversThrown(), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }

  private static String getInningsOverString(ScoreBoard scoreBoard){
    return String.format(INNINGS_OVER_STRING, scoreBoard.getTeamName(), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }
}
