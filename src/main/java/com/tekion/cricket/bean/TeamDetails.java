package com.tekion.cricket.bean;

import lombok.Data;

import java.util.List;

@Data
public class TeamDetails {
    private int teamId;
    private String teamName;
    private List<PlayerDetails> playersDetails;
}
