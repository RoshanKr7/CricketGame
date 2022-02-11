package com.tekion.cricket.service;

import com.tekion.cricket.bean.ScoreBoard;

import java.util.List;
import java.util.Map;

public class ScoreBoardService {
    public void initialiseScoreboard(ScoreBoard scoreBoard, String teamName){
        scoreBoard.setTeamName(teamName);
    }

    public void addWicketFallen(ScoreBoard scoreBoard){
        scoreBoard.setWicketFallen(scoreBoard.getWicketFallen() + 1);
    }

    public void startNewPartnership(ScoreBoard scoreBoard){
        List<Integer> partnership = scoreBoard.getPartnerships();
        partnership.add(0);
        scoreBoard.setPartnerships(partnership);
    }

    public void AddScoreToPartnerships(ScoreBoard scoreBoard, int score) {
        List<Integer> partnership = scoreBoard.getPartnerships();
        partnership.set(scoreBoard.getWicketFallen(), partnership.get(scoreBoard.getWicketFallen()) + score);
        scoreBoard.setPartnerships(partnership);
    }

    public void incrementOver(ScoreBoard scoreBoard, Map<Integer, String> balls){
        List<Map<Integer, String>> overs = scoreBoard.getOvers();
        overs.add(balls);
        scoreBoard.setOvers(overs);
        scoreBoard.setOversThrown(scoreBoard.getOversThrown() + 1);
    }

    public void updateTeamScore(ScoreBoard scoreBoard, int score) {
        scoreBoard.setTeamScore(scoreBoard.getTeamScore() + score);
    }

    public int currentPartnerships(ScoreBoard scoreBoard){
        return scoreBoard.getPartnerships().get(scoreBoard.getWicketFallen()-1);
    }

}
