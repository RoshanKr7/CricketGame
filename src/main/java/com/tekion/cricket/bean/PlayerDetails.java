package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class PlayerDetails {
    private int playerCode;
    private String playerName;
    private PlayerType playerType;
    private int battingRating;
    private int bowlingRating;
}
