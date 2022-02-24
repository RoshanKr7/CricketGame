package com.tekion.cricket.service;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.TeamDetails;

import java.util.List;

public interface IBowlingScoreCardService {
    void initialiseBowler(List<BowlingScoreCard> bowlingScoreCards, TeamDetails bowlingTeam, Integer currentBowler, int inningsNumber);

    Boolean containsBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler);

    void addScoreToBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler, int score);

    void addWicketToBowler(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler);

    void endOfOver(List<BowlingScoreCard> bowlingScoreCards, Integer currentBowler);

    void addMatchIdToAllBowler(Match match);

    List<BowlingScoreCard> findAllBowlingScoreCard();

    List<BowlingScoreCard> findBowlingScoreCardByMatchId(Integer matchId);

    List<BowlingScoreCard> findBowlingScoreCardByMatchIdAndTeamId(Integer matchId, Integer teamId);
}
