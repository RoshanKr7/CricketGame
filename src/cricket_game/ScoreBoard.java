package cricket_game;

import lombok.Data;

import java.util.List;

@Data

public class ScoreBoard {

    private String teamName;
    private int numberOfBalls;
    private int partnerships;
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
        return partnerships;
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

    public void setPartnerships(int partnerships) {
        this.partnerships = partnerships;
    }

    public void setWicketFallen(int wicketFallen) {
        this.wicketFallen = wicketFallen;
    }

    public void setBallsThrown(int ballsThrown) {
        this.ballsThrown = ballsThrown;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public void updatePartnerships(int score){
        partnerships += score;
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
