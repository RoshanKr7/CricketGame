package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class BattingScoreCard {
    private int playerCode;
    private String playerName;
    private int runs;
    private int noOfBallsPlayed;
    private int fours;
    private int sixes;
}
