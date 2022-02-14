package com.tekion.cricket.service;

import com.tekion.cricket.bean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class ScoreBoardService {
    private BattingScoreCardService battingScoreBoardService = new BattingScoreCardService();
    private Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(ScoreBoardService.class.getName());

    public void initialiseScoreboard(ScoreBoard scoreBoard, String teamName){
        if(teamName == null){
            System.out.println("Problem in function initialiseScoreboard");
        }
        scoreBoard.setTeamName(teamName);
    }

    public void initialiseOpeningBatters(ScoreBoard scoreBoard, List<BattingScoreCard> battingScoreCards, PlayerDetails batterOne, PlayerDetails batterTwo){
        if(batterOne == null || batterTwo == null){
            System.out.println("Problem in function initialiseCurrentBatters");
        }
        else{
            List<Integer> currentBatters = new ArrayList<>();
            currentBatters.add(batterOne.getPlayerCode());
            currentBatters.add(batterTwo.getPlayerCode());
            scoreBoard.setCurrentBatters(currentBatters);

            battingScoreBoardService.initialiseBatter(battingScoreCards, batterOne);
            battingScoreBoardService.initialiseBatter(battingScoreCards, batterTwo);
        }
    }

    public Integer updateBowlerCode(TeamDetails bowlingTeam, Integer currentBowler){
        Integer bowlerCode;
        while (true) {
            bowlerCode = scanner.nextInt();
            if(bowlerCode == currentBowler){
                logger.info("Same Bowler can't bowl continuous Overs");
            }
            else if (bowlerCode >= 0 && bowlerCode < bowlingTeam.getPlayersDetails().size()) {
                break;
            }
            else{
                logger.info("Please Enter valid Player Code");
            }
        }
        return bowlerCode;
    }

    public void addWicketFallen(ScoreBoard scoreBoard){
        if(scoreBoard == null){
            System.out.println("Problem in function addWicketFallen");
        }
        else {
            scoreBoard.setWicketFallen(scoreBoard.getWicketFallen() + 1);
        }
    }

    public void startNewPartnership(ScoreBoard scoreBoard){
        if(scoreBoard == null){
            System.out.println("Problem in function startNewPartnership");
        }
        else{
            List<Integer> partnership = scoreBoard.getPartnerships();
            partnership.add(0);
            scoreBoard.setPartnerships(partnership);
        }
    }

    public void addScoreToPartnerships(ScoreBoard scoreBoard, int score) {
        List<Integer> partnership = scoreBoard.getPartnerships();
        if(partnership == null){
            System.out.println("Problem in function addScoreToPartnerships");
        }
        else{
            partnership.set(scoreBoard.getWicketFallen(), partnership.get(scoreBoard.getWicketFallen()) + score);
            scoreBoard.setPartnerships(partnership);
        }
    }

    public void incrementOver(ScoreBoard scoreBoard, Map<Integer, String> balls){
        Over over = new Over();
        List<Over> overs = scoreBoard.getOvers();
        if(balls == null){
            System.out.println("Problem in function incrementOver");
        }
        else{
            over.setBalls(balls);
            overs.add(over);
            scoreBoard.setOvers(overs);
            scoreBoard.setOversThrown(scoreBoard.getOversThrown() + 1);
        }
    }

    public void updateTeamScore(ScoreBoard scoreBoard, int score) {
        if(scoreBoard == null){
            System.out.println("Problem in function updateTeamScore");
        }
        else{
            scoreBoard.setTeamScore(scoreBoard.getTeamScore() + score);
        }
    }

    public int currentPartnerships(ScoreBoard scoreBoard){
        if(scoreBoard == null){
            System.out.println("Problem in function currentPartnerships");
            return 0;
        }
        else{
            return scoreBoard.getPartnerships().get(scoreBoard.getWicketFallen()-1);
        }
    }

}
