package com.tekion.cricket.controller;

import com.tekion.cricket.bean.BowlingScoreCard;
import com.tekion.cricket.exception.ResourceNotFoundException;
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
    @GetMapping("{id}")
    public ResponseEntity<BowlingScoreCard> getBowlingScoreCardById(@PathVariable Integer id){
        BowlingScoreCard bowlingScoreCard = bowlingScoreCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bowling ScoreCard not exist with id:" + id));
        return ResponseEntity.ok(bowlingScoreCard);
    }
}
