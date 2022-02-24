package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BattingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBattingScoreCardRepository extends JpaRepository<BattingScoreCard, Integer> {

    List<BattingScoreCard> findByMatchId(Integer matchId);

    List<BattingScoreCard> findByMatchIdAndTeamId(Integer matchId, Integer teamId);
}
