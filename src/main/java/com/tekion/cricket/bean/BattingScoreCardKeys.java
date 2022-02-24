package com.tekion.cricket.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BattingScoreCardKeys implements Serializable {
    private int matchId;
    private int teamId;
    private int playerCode;
}
