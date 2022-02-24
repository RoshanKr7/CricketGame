package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.MatchSummary;

public class MatchSummaryService implements IMatchSummaryService{
    public MatchSummary finaliseMatchSummary(Match match){
        MatchSummary matchSummary = new MatchSummary();
        matchSummary.setTeam1(match.getTeamOneName());
        matchSummary.setTeam2(match.getTeamTwoName());
        matchSummary.setTossWinner(match.getTossWinnerName());
        return matchSummary;
    }


}
