package com.tekion.cricket.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(BattingScoreCardKeys.class)
@Table(name = "Batting_Scorecard")
public class BattingScoreCard {
    @Id
    private int matchId;
    @Id
    private int inningsNumber;
    @Id
    private int playerCode;
    private String playerName;
    @Column(nullable = true)
    private int runs;
    @Column(nullable = true)
    private int noOfBallsPlayed;
    @Column(nullable = true)
    private int fours;
    @Column(nullable = true)
    private int sixes;
}
