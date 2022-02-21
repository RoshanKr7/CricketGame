package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BowlingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BowlingScoreCardRepository extends JpaRepository<BowlingScoreCard, Integer> {

}
