package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.MatchSummary;
import com.tekion.cricket.exception.ResourceNotFoundException;
import com.tekion.cricket.repo.IMatchSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchSummaryService implements IMatchSummaryService{
    @Autowired
    private IMatchSummaryRepository matchSummaryRepository;

    public MatchSummary finaliseMatchSummary(Match match){
        MatchSummary matchSummary = new MatchSummary();
        matchSummary.setTeam1(match.getTeamOneName());
        matchSummary.setTeam2(match.getTeamTwoName());
        matchSummary.setTossWinner(match.getTossWinnerName());
        return matchSummary;
    }

    public List<MatchSummary> findAllMatchSummary(){
        return matchSummaryRepository.findAll();
    }

    public MatchSummary findMatchSummaryById(Integer matchId){
        return matchSummaryRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match Summary not exist with id:" + matchId));
    }

    public int findMatchCount(){
        return (int)matchSummaryRepository.count();
    }
}
