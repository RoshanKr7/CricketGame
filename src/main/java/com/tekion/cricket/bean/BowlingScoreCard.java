package com.tekion.cricket.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@IdClass(BowlingScoreCardKeys.class)
@Table(name = "Bowling_Scorecard")
public class BowlingScoreCard {
    @Id
    private int matchId;
    @Id
    private int teamId;
    @Id
    private int playerCode;
    private int noOfOvers;
    private int noOfBalls;
    private int runs;
    private int wickets;
}
