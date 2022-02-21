package com.tekion.cricket.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "player_details")
public class PlayerDetails {
    public enum PlayerType{
        BAT,
        BALL
    }

    @Id
    private int playerCode;
    private String playerName;
    private PlayerType playerType;
    private int battingRating;
    private int bowlingRating;
}
