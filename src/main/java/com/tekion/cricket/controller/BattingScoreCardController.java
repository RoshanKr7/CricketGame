package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.repo.BattingScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/battingScoreCard")
public class BattingScoreCardController {
    @Autowired
    private BattingScoreCardRepository battingScoreCardRepository;

    @GetMapping
    public List<BattingScoreCard> getAllBattingScoreCard(){
        return battingScoreCardRepository.findAll();
    }

    // Get Batting ScoreCard By matchId
    @GetMapping("{matchId}")
    public ResponseEntity<List<BattingScoreCard>> getBattingScoreCardByMatchId(@PathVariable Integer matchId){
        List<BattingScoreCard> battingScoreCards = battingScoreCardRepository.findByMatchId((matchId));
        return ResponseEntity.ok(battingScoreCards);
    }

    // Get Batting ScoreCard By matchId and Innings
    @GetMapping("{matchId}/{inningsNumber}")
    public ResponseEntity<List<BattingScoreCard>> getBattingScoreCardByMatchIdAndInnings(
            @PathVariable(name = "matchId") Integer matchId, @PathVariable(name = "inningsNumber") Integer inningsNumber){
        List<BattingScoreCard> battingScoreCards = battingScoreCardRepository.findByMatchIdAndInningsNumber(matchId, inningsNumber);
        return ResponseEntity.ok(battingScoreCards);
    }
}
