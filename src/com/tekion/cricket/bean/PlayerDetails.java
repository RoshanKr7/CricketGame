package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class PlayerDetails {
    public enum PlayerType{
        BAT,
        BALL
    }

    private int playerCode;
    private String playerName;
    private PlayerType playerType;
    private int battingRating;
    private int bowlingRating;
}
