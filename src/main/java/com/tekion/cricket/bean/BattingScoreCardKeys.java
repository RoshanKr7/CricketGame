package com.tekion.cricket.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BattingScoreCardKeys implements Serializable {
    private int matchId;
    private int inningsNumber;
    private int playerCode;
}