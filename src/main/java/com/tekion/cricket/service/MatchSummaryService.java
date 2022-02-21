package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.MatchSummary;

public class MatchSummaryService {
    public MatchSummary finaliseMatchSummary(Match match){
        MatchSummary matchSummary = new MatchSummary();
        matchSummary.setTeam1(match.getTeamOneName());
        matchSummary.setTeam2(match.getTeamTwoName());
        matchSummary.setTossWinner(match.getTossWinner().getTeamName());
        return matchSummary;
    }
}
