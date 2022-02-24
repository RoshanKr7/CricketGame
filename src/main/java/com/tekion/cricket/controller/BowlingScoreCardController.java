package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.service.IBowlingScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scorecards/bowlers/")
public class BowlingScoreCardController {
    @Autowired(required = false)
    private IBowlingScoreCardService bowlingScoreCardService;

    @GetMapping
    public List<BowlingScoreCard> getAllBowlingScoreCard(){
        return bowlingScoreCardService.findAllBowlingScoreCard();
    }

    // Get bowling ScoreCard By matchId
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<List<BowlingScoreCard>> getBowlingScoreCardByMatchId(@PathVariable Integer matchId){
        return ResponseEntity.ok(bowlingScoreCardService.findBowlingScoreCardByMatchId(matchId));
    }

    // Get bowling ScoreCard By matchId and Innings
    @GetMapping("/matches/{matchId}/teams/{teamId}")
    public ResponseEntity<List<BowlingScoreCard>> getBowlingScoreCardByMatchIdAndInnings(
            @PathVariable(name = "matchId") Integer matchId, @PathVariable(name = "teamId") Integer teamId){
        return ResponseEntity.ok(bowlingScoreCardService.findBowlingScoreCardByMatchIdAndTeamId(matchId, teamId));
    }
}
