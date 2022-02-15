package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class BowlingScoreCard {
    private int playerCode;
    private String playerName;
    private int noOfOvers;
    private int noOfBalls;
    private int runs;
    private int wickets;
}
