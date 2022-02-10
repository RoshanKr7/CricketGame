package com.tekion.cricket.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data

public class ScoreBoard {

  private String teamName;
  private int numberOfBalls;
  public List<Integer> partnerships = new ArrayList<>();
  private int wicketFallen;
  private int ballsThrown;
  private int teamScore;

}
