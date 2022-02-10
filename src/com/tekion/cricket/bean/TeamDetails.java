package com.tekion.cricket.bean;

import lombok.Data;

import java.util.List;

@Data
public class TeamDetails {
    private String teamName;
    List<PlayerDetails> playersDetails;
}
