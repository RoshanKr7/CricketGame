package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BattingScoreCard;
import com.tekion.cricket.repo.BattingScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
//    @GetMapping("{id}")
//    public ResponseEntity<List<BattingScoreCard>> getBattingScoreCardById(@PathVariable Integer id){
//        List<BattingScoreCard> battingScoreCard = battingScoreCardRepository.findById(id);
//        return ResponseEntity.ok(battingScoreCard);
//    }

}
