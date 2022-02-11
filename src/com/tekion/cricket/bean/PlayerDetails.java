package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class PlayerDetails {
    private String playerName;
    private String playerType;
    private int battingRating;
    private int bowlingRating;
}
