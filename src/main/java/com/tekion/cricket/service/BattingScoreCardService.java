package com.tekion.cricket.service;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.PlayerDetails;

import java.util.List;

public class BattingScoreCardService {
    public void initialiseBatter(List<BattingScoreCard> battingScoreCards, PlayerDetails batter, int inningsNumber){
        BattingScoreCard battingScoreCard = new BattingScoreCard();
        battingScoreCard.setInningsNumber(inningsNumber);
        battingScoreCard.setPlayerCode(batter.getPlayerCode());
        battingScoreCard.setPlayerName(batter.getPlayerName());
        battingScoreCard.setRuns(0);
        battingScoreCard.setNoOfBallsPlayed(0);
        battingScoreCard.setFours(0);
        battingScoreCard.setSixes(0);
        battingScoreCards.add(battingScoreCard);
    }

    public void addScoreToBatter(List<BattingScoreCard> battingScoreCards, int currentBatterCode, int score){
        BattingScoreCard battingScoreCard = battingScoreCards.get(currentBatterCode);
        battingScoreCard.setRuns(battingScoreCard.getRuns() + score);
        battingScoreCard.setNoOfBallsPlayed(battingScoreCard.getNoOfBallsPlayed() + 1);
        if(score == 4){
            battingScoreCard.setFours(battingScoreCard.getFours() + 1);
        }
        else if(score == 6){
            battingScoreCard.setSixes(battingScoreCard.getSixes() + 1);
        }
    }

    public void batterInningOver(List<BattingScoreCard> battingScoreCards, int currentBatterCode){
        addScoreToBatter(battingScoreCards, currentBatterCode, 0);
    }

    public void addMatchIdToAllBatters(Match match){
        int matchId = match.getMatchSummary().getMatchId();
        for (BattingScoreCard battingScoreCard : match.getScoreBoardInnings1().getBattingScoreCard()){
            battingScoreCard.setMatchId(matchId);
        }
        for (BattingScoreCard battingScoreCard : match.getScoreBoardInnings2().getBattingScoreCard()){
            battingScoreCard.setMatchId(matchId);
        }
    }
}
