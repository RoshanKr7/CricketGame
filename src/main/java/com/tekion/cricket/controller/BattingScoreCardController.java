package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.service.IBattingScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scorecards/batters")
public class BattingScoreCardController {
    @Autowired(required = false)
    private IBattingScoreCardService battingScoreCardService;

    @GetMapping
    public List<BattingScoreCard> getAllBattingScoreCard(){
        return battingScoreCardService.findAllBattingScoreCard();
    }

    // Get Batting ScoreCard By matchId
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<List<BattingScoreCard>> getBattingScoreCardByMatchId(@PathVariable("matchId") Integer matchId){
        return ResponseEntity.ok(battingScoreCardService.findBattingScoreCardByMatchId(matchId));
    }

    // Get Batting ScoreCard By matchId and Innings
    @GetMapping("/matches/{matchId}/teams/{teamId}")
    public ResponseEntity<List<BattingScoreCard>> getBattingScoreCardByMatchIdAndTeamId(
            @PathVariable(name = "matchId") Integer matchId, @PathVariable("teamId") Integer teamId){
        return ResponseEntity.ok(battingScoreCardService.findBattingScoreCardByMatchIdAndTeamId(matchId, teamId));
    }
}
