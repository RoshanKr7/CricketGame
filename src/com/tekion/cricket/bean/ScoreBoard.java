package com.tekion.cricket.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data

public class ScoreBoard {

  private String teamName;
  private List<Integer> partnerships = new ArrayList<>();
  private List<Map<Integer, String>> overs;
  private int wicketFallen;
  private int oversThrown;
  private int teamScore;

}
