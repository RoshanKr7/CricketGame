package com.tekion.cricket.service;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.PlayerDetails;

import java.util.List;

public interface IBattingScoreCardService {
    void initialiseBatter(List<BattingScoreCard> battingScoreCards, PlayerDetails batter, int inningsNumber);

    void addScoreToBatter(List<BattingScoreCard> battingScoreCards, int currentBatterCode, int score);

    void batterInningOver(List<BattingScoreCard> battingScoreCards, int currentBatterCode);

    void addMatchIdToAllBatters(Match match);

    List<BattingScoreCard> findAllBattingScoreCard();

    List<BattingScoreCard> findBattingScoreCardByMatchId(Integer matchId);

    List<BattingScoreCard> findBattingScoreCardByMatchIdAndTeamId(Integer matchId, Integer teamId);
}
