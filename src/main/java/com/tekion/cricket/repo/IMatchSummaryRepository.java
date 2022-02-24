package com.tekion.cricket.repo;

import com.tekion.cricket.bean.MatchSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMatchSummaryRepository extends JpaRepository<MatchSummary, Integer> {

}
