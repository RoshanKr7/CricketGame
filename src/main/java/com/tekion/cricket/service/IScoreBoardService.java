package com.tekion.cricket.service;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.ScoreBoard;
import com.tekion.cricket.bean.TeamDetails;

import java.util.List;
import java.util.Map;

public interface IScoreBoardService {
    void initialiseScoreboard(ScoreBoard scoreBoard, String teamName);

    void initialiseOpeningBatters(ScoreBoard scoreBoard, List<BattingScoreCard> battingScoreCards, PlayerDetails batterOne,
                                  PlayerDetails batterTwo, int inningsNumber);

    Integer updateBowlerCode(TeamDetails bowlingTeam, Integer currentBowler);

    void addWicketFallen(ScoreBoard scoreBoard);

    void startNewPartnership(ScoreBoard scoreBoard);

    void addScoreToPartnerships(ScoreBoard scoreBoard, int score);

    void incrementOver(ScoreBoard scoreBoard, Map<Integer, String> balls);

    void updateTeamScore(ScoreBoard scoreBoard, int score);

    int currentPartnerships(ScoreBoard scoreBoard);
}
