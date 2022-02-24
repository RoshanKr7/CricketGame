package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BowlingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBowlingScoreCardRepository extends JpaRepository<BowlingScoreCard, Integer> {

    List<BowlingScoreCard> findByMatchId(Integer matchId);

    List<BowlingScoreCard> findByMatchIdAndTeamId(Integer matchId, Integer teamId);
}
