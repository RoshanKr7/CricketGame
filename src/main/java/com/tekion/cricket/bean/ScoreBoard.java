package com.tekion.cricket.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ScoreBoard {

  private String teamName;
  private List<Integer> partnerships = new ArrayList<>();
  private List<Over> overs = new ArrayList<>();
  private int wicketFallen;
  private int oversThrown;
  private int teamScore;
  private List<Integer> currentBatters = new ArrayList<>();
  private Integer currentBowler;
  private List<BattingScoreCard> battingScoreCard = new ArrayList<>();
  private List<BowlingScoreCard> bowlingScoreCard = new ArrayList<>();
}
