package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;

public class MatchService implements IMatchService{
    public void addMatchId(Match match){
        match.setMatchId(match.getMatchSummary().getMatchId());
    }
}
