package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class Match {
    private String teamOneName;
    private String teamTwoName;
    private int numberOfOvers;
    private TeamDetails tossWinner;
    private TeamDetails battingFirstTeam;
    private TeamDetails bowlingFirstTeam;
    private ScoreBoard scoreBoardInnings1;
    private ScoreBoard scoreBoardInnings2;
    private MatchSummary matchSummary;
}
