package com.tekion.cricket.repo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ScoreBoard {

    private String teamName;
    private int numberOfBalls;
    private List<Integer> partnerships = new ArrayList<>();
    private int wicketFallen;
    private int ballsThrown;
    private int teamScore;

    public String getTeamName() {
        return teamName;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public int getPartnerships() {
        return partnerships.get(wicketFallen-1);
    }

    public int getWicketFallen() {
        return wicketFallen;
    }

    public int getBallsThrown() {
        return ballsThrown;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public void setPartnerships(){
        partnerships.add(0);
    }

    public void updatePartnerships(int score){
        partnerships.set(wicketFallen, partnerships.get(wicketFallen)+score);
    }

    public void updateWicketFallen(){
        wicketFallen += 1;
    }

    public void updateBallsThrown(){
        ballsThrown += 1;
    }

    public void updateTeamScore(int score){
        teamScore += score;
    }

}
