package com.tekion.cricket.util;

import com.tekion.cricket.bean.*;
import com.tekion.cricket.service.BattingScoreCardService;
import com.tekion.cricket.service.BowlingScoreCardService;
import com.tekion.cricket.service.ScoreBoardService;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class InningsUtil {
  private final String INNINGS_START_STRING = "Team %s is going to start their innings";
  private final String PARTNERSHIP_BROKEN_STRING =  "Wicket Number %1$d has Fallen \n Partnership for Wicket %1$d is %2$d";
  private final String AFTER_EACH_OVER_STRING = "After Over %d : %d/%d";
  private final String INNINGS_OVER_STRING = "Team %s Innings Over \nFinal Score : %d/%d\n\n";

  private ScoreBoardService scoreBoardService = new ScoreBoardService();
  private BattingScoreCardService battingScoreCardService = new BattingScoreCardService();
  private BowlingScoreCardService bowlingScoreCardService = new BowlingScoreCardService();
  private int inningsNumber;

  public ScoreBoard playFirstInning(Match match) {
    ScoreBoard scoreBoard = new ScoreBoard();
    Map<Integer, String> balls = new HashMap<>();
    TeamDetails battingTeam = match.getBattingFirstTeam();
    TeamDetails bowlingTeam = match.getBowlingFirstTeam();
    List<BattingScoreCard> battingScoreCards = new ArrayList<>();
    List<BowlingScoreCard> bowlingScoreCards = new ArrayList<>();
    inningsNumber = 1;
    scoreBoardService.initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));

    PlayerDetails batterOne = battingTeam.getPlayersDetails().get(0);
    PlayerDetails batterTwo = battingTeam.getPlayersDetails().get(1);

    scoreBoardService.initialiseOpeningBatters(scoreBoard, battingScoreCards, batterOne, batterTwo, inningsNumber);
    scoreBoardService.startNewPartnership(scoreBoard);
    System.out.println("Enter Bowler Code");
    scoreBoard.setCurrentBowler(scoreBoardService.updateBowlerCode(bowlingTeam, -1));
    Integer currentBowler = scoreBoard.getCurrentBowler();
    if(!(bowlingScoreCardService.containsBowler(bowlingScoreCards, currentBowler))){
      bowlingScoreCardService.initialiseBowler(bowlingScoreCards, bowlingTeam, currentBowler, inningsNumber);
    }

    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(match, scoreBoard, battingTeam, bowlingTeam, battingScoreCards, bowlingScoreCards, balls);
    }

    System.out.println(getInningsOverString(scoreBoard));
    scoreBoard.setBattingScoreCard(battingScoreCards);
    scoreBoard.setBowlingScoreCard(bowlingScoreCards);
    return scoreBoard;
  }

  public ScoreBoard playSecondInning(Match match){
    ScoreBoard scoreBoard = new ScoreBoard();
    Map<Integer, String> balls = new HashMap<>();
    TeamDetails battingTeam = match.getBowlingFirstTeam();
    TeamDetails bowlingTeam = match.getBattingFirstTeam();
    List<BattingScoreCard> battingScoreCards = new ArrayList<>();
    List<BowlingScoreCard> bowlingScoreCards = new ArrayList<>();
    inningsNumber = 2;
    scoreBoardService.initialiseScoreboard(scoreBoard, battingTeam.getTeamName());

    System.out.println(String.format(INNINGS_START_STRING, battingTeam.getTeamName()));

    PlayerDetails batterOne = battingTeam.getPlayersDetails().get(0);
    PlayerDetails batterTwo = battingTeam.getPlayersDetails().get(1);

    scoreBoardService.initialiseOpeningBatters(scoreBoard, battingScoreCards, batterOne, batterTwo, inningsNumber);
    scoreBoardService.startNewPartnership(scoreBoard);
    System.out.println("Enter Bowler Code");
    scoreBoard.setCurrentBowler(scoreBoardService.updateBowlerCode(bowlingTeam, -1));
    Integer currentBowler = scoreBoard.getCurrentBowler();
    if(!(bowlingScoreCardService.containsBowler(bowlingScoreCards, currentBowler))){
      bowlingScoreCardService.initialiseBowler(bowlingScoreCards, bowlingTeam, currentBowler, inningsNumber);
    }

    while(scoreBoard.getWicketFallen() < Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() < match.getNumberOfOvers()){
      updateScoreBoardForEachBall(match, scoreBoard, battingTeam, bowlingTeam, battingScoreCards, bowlingScoreCards, balls);
      if (scoreBoard.getTeamScore() > match.getScoreBoardInnings1().getTeamScore()) {
        break;
      }
    }

    System.out.println(getInningsOverString(scoreBoard));
    scoreBoard.setBattingScoreCard(battingScoreCards);
    scoreBoard.setBowlingScoreCard(bowlingScoreCards);
    return scoreBoard;
  }

  private List<Integer> makeScoreChances(PlayerDetails batter, PlayerDetails bowler){
    int batterRating = batter.getBattingRating();
    int bowlerRating = bowler.getBowlingRating();
    List<Integer> scoreChances = new ArrayList<>();
    int totalScore = 0;
    totalScore += (bowlerRating - batterRating > -3 ? (8 + 3 * (bowlerRating-batterRating)) : 2);   // For Score 0 (20)
    scoreChances.add(totalScore);
    totalScore += (bowlerRating - batterRating > -6 ? (15 + 2 * (bowlerRating - batterRating)) : 5);   // For Score 1 (45)
    scoreChances.add(totalScore);
    totalScore += (bowlerRating - batterRating > -11 ? (15 + 1 * (bowlerRating - batterRating)) : 5);   // For Score 2 (65)
    scoreChances.add(totalScore);
    totalScore += (bowlerRating - batterRating > -3 ? 7 : 4);   // For Score 3 (72)
    scoreChances.add(totalScore);
    totalScore += (batterRating - bowlerRating > -8 ? (10 + 1 * (batterRating - bowlerRating)) : 3);    // For Score 4 (87)
    scoreChances.add(totalScore);
    totalScore += (bowlerRating - batterRating > -3 ? 3 : 1);  // For Score 5 (89)
    scoreChances.add(totalScore);
    totalScore += (batterRating - bowlerRating > -4 ? (6 + 2 * (batterRating - bowlerRating)) : 1);    // For Score 6 (99)
    scoreChances.add(totalScore);
    totalScore += (bowlerRating - batterRating > -5 ? (6 + 1 * (bowlerRating - batterRating)) : 2);    // For Wicket (107)
    scoreChances.add(totalScore);
    return scoreChances;
  }

  private void updateScoreBoardForEachBall(Match match, ScoreBoard scoreBoard,TeamDetails battingTeam, TeamDetails bowlingTeam, List<BattingScoreCard> battingScoreCards,
                                           List<BowlingScoreCard> bowlingScoreCards, Map<Integer, String> balls){
    Integer score;
    List<Integer> currentBatters = scoreBoard.getCurrentBatters();
    List<Integer> scoreChances = makeScoreChances(battingTeam.getPlayersDetails().get(currentBatters.get(0)),
            bowlingTeam.getPlayersDetails().get(scoreBoard.getCurrentBowler()));

    score = CricketUtility.scoreGenerator(scoreChances, scoreChances.get(scoreChances.size() - 1));

    if (score == Constants.CONSTANT_FOR_OUT) {
      updateScoreBoardForWicket(scoreBoard, battingTeam, battingScoreCards, bowlingScoreCards, balls);
    } else {
      balls.put(balls.size(), score.toString());
      scoreBoardService.addScoreToPartnerships(scoreBoard, score);
      scoreBoardService.updateTeamScore(scoreBoard, score);
      battingScoreCardService.addScoreToBatter(battingScoreCards, currentBatters.get(0), score);
      bowlingScoreCardService.addScoreToBowler(bowlingScoreCards, scoreBoard.getCurrentBowler(), score);
      if(score % 2 == 1){
        Collections.swap(currentBatters, 0, 1);
        scoreBoard.setCurrentBatters(currentBatters);
      }
    }
    if(balls.size() == Constants.NUMBER_OF_BALL_IN_OVER){
      scoreBoardService.incrementOver(scoreBoard, balls);
      balls.clear();
      bowlingScoreCardService.endOfOver(bowlingScoreCards, scoreBoard.getCurrentBowler());
      System.out.println(getAfterEachOverString(scoreBoard));

      if(scoreBoard.getWicketFallen() != Constants.NUMBER_WICKETS && scoreBoard.getOversThrown() != match.getNumberOfOvers()){
        System.out.println("Enter Bowler Code");
        scoreBoard.setCurrentBowler(scoreBoardService.updateBowlerCode(bowlingTeam, scoreBoard.getCurrentBowler()));
        Integer currentBowler = scoreBoard.getCurrentBowler();
        if(!bowlingScoreCardService.containsBowler(bowlingScoreCards, currentBowler)){
          bowlingScoreCardService.initialiseBowler(bowlingScoreCards, bowlingTeam, currentBowler, inningsNumber);
        }
      }
    }
  }

  private void updateScoreBoardForWicket(ScoreBoard scoreBoard, TeamDetails battingTeam, List<BattingScoreCard> battingScoreCards,
                                         List<BowlingScoreCard> bowlingScoreCards, Map<Integer, String> balls){
    List<Integer> currentBatters = scoreBoard.getCurrentBatters();
    scoreBoardService.addWicketFallen(scoreBoard);
    System.out.println(getPartnershipBrokenString(scoreBoard));
    scoreBoardService.startNewPartnership(scoreBoard);
    balls.put(balls.size(), "W");

    bowlingScoreCardService.addWicketToBowler(bowlingScoreCards, scoreBoard.getCurrentBowler());
    battingScoreCardService.batterInningOver(battingScoreCards, currentBatters.get(0));
    if(scoreBoard.getWicketFallen() != Constants.NUMBER_WICKETS){
      battingScoreCardService.initialiseBatter(
              battingScoreCards, battingTeam.getPlayersDetails().get(scoreBoard.getWicketFallen()+1), inningsNumber);
      currentBatters.set(0, scoreBoard.getWicketFallen()+1);
      scoreBoard.setCurrentBatters(currentBatters);
    }
    CricketUtility.waitForMilliSec(2000);
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
