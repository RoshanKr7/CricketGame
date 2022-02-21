package com.tekion.cricket.controller;

import com.tekion.cricket.bean.MatchSummary;
import com.tekion.cricket.exception.ResourceNotFoundException;
import com.tekion.cricket.repo.MatchSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matchSummary")
public class MatchSummaryController {
    @Autowired
    private MatchSummaryRepository matchSummaryRepository;

    @GetMapping
    public List<MatchSummary> getAllMatchSummary(){
        return matchSummaryRepository.findAll();
    }

    // Get Match Summary By matchId
    @GetMapping("{id}")
    public ResponseEntity<MatchSummary> getMatchSummaryById(@PathVariable Integer id){
        MatchSummary matchSummary = matchSummaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match Summary not exist with id:" + id));
        return ResponseEntity.ok(matchSummary);
    }

    @GetMapping("/count")
    public int getMatchCount(){
        return (int)matchSummaryRepository.count();
    }
}
