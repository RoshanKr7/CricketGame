package com.tekion.cricket.service;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.repo.IBattingScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BattingScoreCardService implements IBattingScoreCardService{

    @Autowired
    private IBattingScoreCardRepository battingScoreCardRepository;


    public void initialiseBatter(List<BattingScoreCard> battingScoreCards, PlayerDetails batter, int teamId){
        BattingScoreCard battingScoreCard = new BattingScoreCard();
        battingScoreCard.setTeamId(teamId);
        battingScoreCard.setPlayerCode(batter.getPlayerCode());
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

    public List<BattingScoreCard> findAllBattingScoreCard(){
        return battingScoreCardRepository.findAll();
    }

    public List<BattingScoreCard> findBattingScoreCardByMatchId(Integer matchId){
        return battingScoreCardRepository.findByMatchId((matchId));
    }

    public List<BattingScoreCard> findBattingScoreCardByMatchIdAndTeamId(Integer matchId, Integer teamId){
        return battingScoreCardRepository.findByMatchIdAndTeamId(matchId, teamId);
    }
}
