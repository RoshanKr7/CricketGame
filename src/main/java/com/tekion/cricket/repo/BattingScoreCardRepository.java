package com.tekion.cricket.repo;

import com.tekion.cricket.bean.BattingScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattingScoreCardRepository extends JpaRepository<BattingScoreCard, Integer> {

}
