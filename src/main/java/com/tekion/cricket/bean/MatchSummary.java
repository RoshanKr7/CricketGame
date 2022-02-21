package com.tekion.cricket.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Match_Summary")
public class MatchSummary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    private String team1;
    private String team2;
    private String tossWinner;
    private String winningTeam;
}
