package com.tekion.cricket.controller;

import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.repo.PlayerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerDetailsController {

    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

    @GetMapping
    public List<PlayerDetails> getAllPlayers(){
        return playerDetailsRepository.findAll();
    }
    
}
