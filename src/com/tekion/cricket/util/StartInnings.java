package com.tekion.cricket.util;

import com.tekion.cricket.repo.ScoreBoard;

public class StartInnings {
    public static ScoreBoard startFirstInning(String teamName, int numberOfBalls){
        ScoreBoard scoreBoard = new ScoreBoard();
        CricketUtility cricketUtility = new CricketUtility();
        scoreBoard.setTeamName(teamName);
        scoreBoard.setNumberOfBalls(numberOfBalls);
        System.out.println("Team " + teamName + " is going to start their innings");
        CricketUtility.wait(3000);
        scoreBoard.setPartnerships();
        while(scoreBoard.getWicketFallen() < Constants.WICKETS && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()){
            scoreBoard.updateBallsThrown();
            int score;
            score = cricketUtility.scoreGenerator();
            if(score == Constants.OUT){
                scoreBoard.updateWicketFallen();
                System.out.println("Wicket Number " + scoreBoard.getWicketFallen() + " has Fallen");
                System.out.println("Partnership for Wicket "+ scoreBoard.getWicketFallen()+ " is " + scoreBoard.getPartnerships());
                CricketUtility.wait(2000);
                scoreBoard.setPartnerships();
            }
            else{
                scoreBoard.updatePartnerships(score);
                scoreBoard.updateTeamScore(score);
            }
            if((scoreBoard.getBallsThrown()%6) == 0){
                System.out.println("After Over "+ (scoreBoard.getBallsThrown()/6) + " : " + scoreBoard.getTeamScore() +"/" + scoreBoard.getWicketFallen());
                CricketUtility.wait(1000);
            }
        }
        System.out.println("Team " + teamName + " Innings Over \n" + "Final Score : " + scoreBoard.getTeamScore() +"/" + scoreBoard.getWicketFallen());
        CricketUtility.wait(2000);
        return scoreBoard;
    }

    public static ScoreBoard startSecondInning(String teamName, ScoreBoard scoreBoardTeam1, int numberOfBalls) throws InterruptedException {
        ScoreBoard scoreBoard = new ScoreBoard();
        CricketUtility cricketUtility = new CricketUtility();
        scoreBoard.setTeamName(teamName);
        scoreBoard.setNumberOfBalls(numberOfBalls);
        System.out.println("Team " + teamName + " is going to start their innings");
        CricketUtility.wait(3000);
        scoreBoard.setPartnerships();
        while(scoreBoard.getWicketFallen() < Constants.WICKETS && scoreBoard.getBallsThrown() < scoreBoard.getNumberOfBalls()){
            scoreBoard.updateBallsThrown();
            int score;
            score = cricketUtility.scoreGenerator();
            if(score == Constants.OUT){
                scoreBoard.updateWicketFallen();
                System.out.println("Wicket Number " + scoreBoard.getWicketFallen() + " has Fallen");
                System.out.println("Partnership for Wicket "+ scoreBoard.getWicketFallen()+ " is " + scoreBoard.getPartnerships());
                CricketUtility.wait(2000);
                scoreBoard.setPartnerships();
            }
            else{
                scoreBoard.updatePartnerships(score);
                scoreBoard.updateTeamScore(score);
                if(scoreBoard.getTeamScore() > scoreBoardTeam1.getTeamScore()){
                    break;
                }
            }
            if((scoreBoard.getBallsThrown()%Constants.BALL_IN_OVER) == 0){
                System.out.println("After Over "+ (scoreBoard.getBallsThrown()/Constants.BALL_IN_OVER) + " : " + scoreBoard.getTeamScore() +"/" + scoreBoard.getWicketFallen());
                CricketUtility.wait(1000);
            }

        }
        System.out.println("Team " + teamName + " Innings Over \n" + "Final Score : " + scoreBoard.getTeamScore() +"/" + scoreBoard.getWicketFallen());
        CricketUtility.wait(2000);
        return scoreBoard;
    }
}
