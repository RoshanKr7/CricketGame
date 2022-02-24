package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;

public class MatchService {
    public void addMatchId(Match match){
        match.setMatchId(match.getMatchSummary().getMatchId());
    }
}
