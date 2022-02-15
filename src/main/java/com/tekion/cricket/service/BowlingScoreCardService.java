package com.tekion.cricket.service;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.TeamDetails;

import java.util.List;

public class BowlingScoreCardService {
    public void initialiseBowler(List<BowlingScoreCard> bowlingScoreCards, TeamDetails bowlingTeam, Integer currentBowler){
        BowlingScoreCard bowlingScoreCard = new BowlingScoreCard();
        PlayerDetails bowler = bowlingTeam.getPlayersDetails().get(currentBowler);
        bowlingScoreCard.setPlayerCode(currentBowler);
        bowlingScoreCard.setPlayerName(bowler.getPlayerName());
        bowlingScoreCard.setNoOfBalls(0);
        bowlingScoreCard.setNoOfOvers(0);
        bowlingScoreCard.setRuns(0);
        bowlingScoreCard.setWickets(0);

        bowlingScoreCards.add(bowlingScoreCard);
    }

    public Boolean containsBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler){
        Boolean containsBowler = false;
        for(BowlingScoreCard bowlingScoreCard: bowlingScoreCards){
            if(bowlingScoreCard.getPlayerCode() == currentBowler){
                containsBowler = true;
                break;
            }
        }
        return containsBowler;
    }

    public void addScoreToBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler, int score){
        Integer indexOfBowler = indexOfBowler(bowlingScoreCards, currentBowler);
        if(indexOfBowler == -1){
            System.out.println("Problem in addScoreToBowler Function");
        }
        else{
            BowlingScoreCard bowlingScoreCard = bowlingScoreCards.get(indexOfBowler);
            bowlingScoreCard.setNoOfBalls(bowlingScoreCard.getNoOfBalls() + 1);
            bowlingScoreCard.setRuns(bowlingScoreCard.getRuns() + score);
            bowlingScoreCards.set(indexOfBowler, bowlingScoreCard);
        }
    }

    public void addWicketToBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler){
        Integer indexOfBowler = indexOfBowler(bowlingScoreCards, currentBowler);
        if(indexOfBowler == -1){
            System.out.println("Problem in addWicketToBowler Function");
        }
        else{
            BowlingScoreCard bowlingScoreCard = bowlingScoreCards.get(indexOfBowler);
            bowlingScoreCard.setNoOfBalls(bowlingScoreCard.getNoOfBalls() + 1);
            bowlingScoreCard.setWickets(bowlingScoreCard.getWickets() + 1);
            bowlingScoreCards.set(indexOfBowler, bowlingScoreCard);
        }
    }

    public void endOfOver(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler){
        Integer indexOfBowler = indexOfBowler(bowlingScoreCards, currentBowler);
        if(indexOfBowler == -1){
            System.out.println("Problem in endOfOver Function");
        }
        else{
            BowlingScoreCard bowlingScoreCard = bowlingScoreCards.get(indexOfBowler);
            bowlingScoreCard.setNoOfOvers(bowlingScoreCard.getNoOfOvers() + 1);
            bowlingScoreCard.setNoOfBalls(0);
            bowlingScoreCards.set(indexOfBowler, bowlingScoreCard);
        }
    }

    private Integer indexOfBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler){
        Integer indexOfBowler = -1;
        Integer index = 0;
        for(BowlingScoreCard bowlingScoreCard: bowlingScoreCards){
            if(bowlingScoreCard.getPlayerCode() == currentBowler){
                indexOfBowler = index;
                break;
            }
            index++;
        }
        return indexOfBowler;
    }
}
