package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.MatchSummary;

import java.util.List;

public interface IMatchSummaryService {
    MatchSummary finaliseMatchSummary(Match match);

    List<MatchSummary> findAllMatchSummary();

    MatchSummary findMatchSummaryById(Integer matchId);

    int findMatchCount();
}
