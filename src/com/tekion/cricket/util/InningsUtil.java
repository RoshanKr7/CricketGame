package com.tekion.cricket.util;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.service.ScoreBoardService;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class InningsUtil {
  private final String INNINGS_START_STRING = "Team %s is going to start their innings";
  private final String PARTNERSHIP_BROKEN_STRING =  "Wicket Number %1$d has Fallen \n Partnership for Wicket %1$d is %2$d";
  private final String AFTER_EACH_OVER_STRING = "After Over %d : %d/%d";
  private final String INNINGS_OVER_STRING = "Team %s Innings Over \nFinal Score : %d/%d\n\n";

  private List<Integer> scoreChances;
  private ScoreBoardService scoreBoardService = new ScoreBoardService();

  public ScoreBoard startFirstInning(Match match) {
    ScoreBoard scoreBoard = new ScoreBoard();
    Map<Integer, String> balls = new HashMap<>();
    TeamDetails battingTeam = match.getBattingFirstTeam();
    scoreBoardService.initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));
    scoreChances = makeScoreChances();

    scoreBoardService.startNewPartnership(scoreBoard);
    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(scoreBoard, balls);
    }

    System.out.println(getInningsOverString(scoreBoard));
    return scoreBoard;
  }

  public ScoreBoard startSecondInning(Match match, ScoreBoard scoreBoardTeam1){
    ScoreBoard scoreBoard = new ScoreBoard();
    Map<Integer, String> balls = new HashMap<>();
    TeamDetails battingTeam = match.getBowlingFirstTeam();
    scoreBoardService.initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));
    scoreChances = makeScoreChances();

    scoreBoardService.startNewPartnership(scoreBoard);
    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(scoreBoard, balls);
      if (scoreBoard.getTeamScore() > scoreBoardTeam1.getTeamScore()) {
        break;
      }
    }

    System.out.println(getInningsOverString(scoreBoard));
    return scoreBoard;
  }

  private List<Integer> makeScoreChances(){
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

  private void updateScoreBoardForEachBall(ScoreBoard scoreBoard, Map<Integer, String> balls){
    Integer score;
    score = CricketUtility.scoreGenerator(scoreChances, scoreChances.get(scoreChances.size() - 1));

    if (score == Constants.CONSTANT_FOR_OUT) {
      scoreBoardService.addWicketFallen(scoreBoard);
      System.out.println(getPartnershipBrokenString(scoreBoard));
      scoreBoardService.startNewPartnership(scoreBoard);
      balls.put(balls.size(), "W");
      CricketUtility.waitForMilliSec(2000);
    } else {
      balls.put(balls.size(), score.toString());
      scoreBoardService.AddScoreToPartnerships(scoreBoard, score);
      scoreBoardService.updateTeamScore(scoreBoard, score);
    }
    if(balls.size() == Constants.NUMBER_OF_BALL_IN_OVER){
      scoreBoardService.incrementOver(scoreBoard, balls);
      balls.clear();
      System.out.println(getAfterEachOverString(scoreBoard));
    }
  }

  private String getPartnershipBrokenString(ScoreBoard scoreBoard){
    return String.format(PARTNERSHIP_BROKEN_STRING, scoreBoard.getWicketFallen(), scoreBoardService.currentPartnerships(scoreBoard));
  }

  private String getAfterEachOverString(ScoreBoard scoreBoard){
    return String.format(AFTER_EACH_OVER_STRING, scoreBoard.getOversThrown(), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }

  private String getInningsOverString(ScoreBoard scoreBoard){
    return String.format(INNINGS_OVER_STRING, scoreBoard.getTeamName(), scoreBoard.getTeamScore(), scoreBoard.getWicketFallen());
  }
}
