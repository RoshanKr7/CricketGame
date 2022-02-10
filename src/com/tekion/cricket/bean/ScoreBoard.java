package com.tekion.cricket.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data

public class ScoreBoard {

  private String teamName;
  public List<Integer> partnerships = new ArrayList<>();
  public List<Map<Integer, Integer>> overs = new ArrayList<>();
  private int wicketFallen;
  private int oversThrown;
  private int teamScore;

}
