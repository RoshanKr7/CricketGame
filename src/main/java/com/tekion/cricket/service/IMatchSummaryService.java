package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.MatchSummary;

public interface IMatchSummaryService {
    MatchSummary finaliseMatchSummary(Match match);
}
