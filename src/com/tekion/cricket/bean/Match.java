package com.tekion.cricket.bean;

import lombok.Data;

@Data
public class Match {
    int numberOfOvers;
    TeamDetails tossWinner;
    TeamDetails battingFirstTeam;
    TeamDetails bowlingFirstTeam;
}
