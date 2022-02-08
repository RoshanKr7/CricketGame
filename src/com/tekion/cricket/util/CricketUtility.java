package com.tekion.cricket.util;


import com.tekion.cricket.repo.ScoreBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CricketUtility {
    private static Random random = new Random();
    private List<Integer> scoreChances= new ArrayList<>();

    CricketUtility(){
        scoreChances.add(20);
        scoreChances.add(45);
        scoreChances.add(65);
        scoreChances.add(72);
        scoreChances.add(87);
        scoreChances.add(88);
        scoreChances.add(98);
        scoreChances.add(105);
    }

    public int scoreGenerator() {
        int utility = random.nextInt(106);
        int score = 0;
        while(scoreChances.get(score) < utility){
            score++;
        }
        return score;
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    public static void result(ScoreBoard scoreboardTeam1, ScoreBoard scoreboardTeam2){
        if(scoreboardTeam1.getTeamScore() > scoreboardTeam2.getTeamScore()){
            System.out.println("Team " + scoreboardTeam1.getTeamName() +" has won the Match by " + (scoreboardTeam1.getTeamScore()-scoreboardTeam2.getTeamScore()) + " Runs.");
        }
        else if(scoreboardTeam1.getTeamScore() < scoreboardTeam2.getTeamScore()){
            System.out.println("Team "+ scoreboardTeam2.getTeamName() +" has won the Match by " + (10-scoreboardTeam2.getWicketFallen()) + " Wickets and " + (scoreboardTeam2.getNumberOfBalls()-scoreboardTeam2.getBallsThrown()) + " Balls.");
        }
        else{
            System.out.println("!!Draw!!");
        }
    }

}
