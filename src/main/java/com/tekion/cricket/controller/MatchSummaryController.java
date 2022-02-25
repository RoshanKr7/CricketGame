package com.tekion.cricket.controller;

import com.tekion.cricket.bean.MatchSummary;
import com.tekion.cricket.service.IMatchSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchSummaryController {
    @Autowired(required = false)
    private IMatchSummaryService matchSummaryService;

    @GetMapping
    public List<MatchSummary> getAllMatchSummary(){
        return matchSummaryService.findAllMatchSummary();
    }

    // Get Match Summary By matchId
    @GetMapping("/match-id/{matchId}")
    public ResponseEntity<MatchSummary> getMatchSummaryByMatchId(@PathVariable("matchId") Integer matchId){
        return ResponseEntity.ok(matchSummaryService.findMatchSummaryById(matchId));
    }

    @GetMapping("/counts")
    public int getMatchCount(){
        return matchSummaryService.findMatchCount();
    }
}
