package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class Match {
    private String TeamOneName;
    private String TeamTwoName;
    private int numberOfOvers;
    private TeamDetails tossWinner;
    private TeamDetails battingFirstTeam;
    private TeamDetails bowlingFirstTeam;
}
