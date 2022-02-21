package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BowlingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BowlingScoreCardRepository extends JpaRepository<BowlingScoreCard, Integer> {

    List<BowlingScoreCard> findByMatchId(Integer matchId);

    List<BowlingScoreCard> findByMatchIdAndInningsNumber(Integer matchId, Integer inningsNumber);
}
