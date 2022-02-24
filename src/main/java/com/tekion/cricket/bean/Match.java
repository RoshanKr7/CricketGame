package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class Match {
    private int matchId;
    private String teamOneName;
    private String teamTwoName;
    private int numberOfOvers;
    private String tossWinnerName;
    private TeamDetails battingFirstTeam;
    private TeamDetails bowlingFirstTeam;
    private ScoreBoard scoreBoardInnings1;
    private ScoreBoard scoreBoardInnings2;
    private String winningTeam;
    private MatchSummary matchSummary;

}
