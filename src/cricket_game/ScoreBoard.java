package cricket_game;

public class ScoreBoard {
    private int numberOfBalls = 0;
    private int partnerships = 0;
    private int wicketFallen = 0;
    private int ballsThrown = 0;
    private int teamScore = 0;

    public void setNumberOfBalls(int overs){
        numberOfBalls = overs * 6;
    }

    public int getNumberOfBalls(){
        return numberOfBalls;
    }

    public void setPartnerships(int val){
        partnerships += val;
    }

    public int getPartnerships(){
        return partnerships;
    }

    public void setWicketFallen(){
        wicketFallen += 1;
    }

    public int getWicketFallen(){
        return wicketFallen;
    }

    public void setBallsThrown(){
        ballsThrown += 1;
    }

    public int getBallsThrown(){
        return ballsThrown;
    }

    public void setTeamScore(int val){
        teamScore += val;
    }

    public int getTeamScore(){
        return teamScore;
    }
}
