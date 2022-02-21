package com.tekion.cricket.repo;

import com.tekion.cricket.bean.MatchSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchSummaryRepository extends JpaRepository<MatchSummary, Integer> {

}
