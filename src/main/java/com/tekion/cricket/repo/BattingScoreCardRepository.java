package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BattingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattingScoreCardRepository extends JpaRepository<BattingScoreCard, Integer> {

    List<BattingScoreCard> findByMatchId(Integer matchId);

    List<BattingScoreCard> findByMatchIdAndInningsNumber(Integer matchId, Integer inningsNumber);
}
