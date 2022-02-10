package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class Match {
    String TeamOneName;
    String TeamTwoName;
    int numberOfOvers;
    TeamDetails tossWinner;
    TeamDetails battingFirstTeam;
    TeamDetails bowlingFirstTeam;
}
