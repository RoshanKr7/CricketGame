package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.repo.BowlingScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bowlingScoreCard")
public class BowlingScoreCardController {
    @Autowired
    private BowlingScoreCardRepository bowlingScoreCardRepository;

    @GetMapping
    public List<BowlingScoreCard> getAllBowlingScoreCard(){
        return bowlingScoreCardRepository.findAll();
    }

    // Get bowling ScoreCard By matchId
    @GetMapping("{matchId}")
    public ResponseEntity<List<BowlingScoreCard>> getBowlingScoreCardByMatchId(@PathVariable Integer matchId){
        List<BowlingScoreCard> bowlingScoreCards = bowlingScoreCardRepository.findByMatchId((matchId));
        return ResponseEntity.ok(bowlingScoreCards);
    }

    // Get bowling ScoreCard By matchId and Innings
    @GetMapping("{matchId}/{inningsNumber}")
    public ResponseEntity<List<BowlingScoreCard>> getBowlingScoreCardByMatchIdAndInnings(
            @PathVariable(name = "matchId") Integer matchId, @PathVariable(name = "inningsNumber") Integer inningsNumber){
        List<BowlingScoreCard> bowlingScoreCards = bowlingScoreCardRepository.findByMatchIdAndInningsNumber(matchId, inningsNumber);
        return ResponseEntity.ok(bowlingScoreCards);
    }
}
