package com.tekion.cricket.service;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.TeamDetails;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CricketService {
  private static Random random = new Random();
  private static Scanner scanner = new Scanner(System.in);
  private static Logger logger = Logger.getLogger(CricketService.class.getName());

  public static int getNumberOfOvers(){
    int overs;
    while (true) {
      logger.info("\nNo. of Overs in the Match : ");
      overs = scanner.nextInt();
      if (overs > 0) {
        break;
      }
      logger.info("Please Enter a valid number greater than 0");
    }
    return overs;
  }

  public static TeamDetails toss(TeamDetails teamOneDetails, TeamDetails teamTwoDetails){
    int tossResult = random.nextInt(2);
    if(tossResult == 0){
      return teamOneDetails;
    }
    else{
      return teamTwoDetails;
    }
  }

  public static void chooseBatOrField(TeamDetails teamOneDetails, TeamDetails teamTwoDetails, Match match){
    int battingChoice;
    while (true) {
      System.out.println("Press 0 for Bat First \nPress 1 for Field First");
      battingChoice = scanner.nextInt();
      if (battingChoice == 0 || battingChoice == 1) {
        break;
      } else {
        System.out.println("!! Please Choose a valid input !!");
      }
    }
    if(battingChoice == 0){
      match.setBattingFirstTeam(match.getTossWinner());
      if(match.getTossWinner().equals(teamOneDetails)){
        match.setBowlingFirstTeam(teamTwoDetails);
      }
      else{
        match.setBowlingFirstTeam(teamOneDetails);
      }
    }
    else{
      match.setBowlingFirstTeam(match.getTossWinner());
      if(match.getTossWinner().equals(teamOneDetails)){
        match.setBattingFirstTeam(teamTwoDetails);
      }
      else{
        match.setBattingFirstTeam(teamOneDetails);
      }
    }
  }

}
