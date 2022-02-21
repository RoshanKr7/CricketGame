package com.tekion.cricket.util;

import com.tekion.cricket.bean.Match;
import com.tekion.cricket.bean.TeamDetails;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.Scanner;

@UtilityClass
public class TossUtil {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public TeamDetails toss(TeamDetails teamOneDetails, TeamDetails teamTwoDetails){
        int tossResult = random.nextInt(2);
        if(tossResult == 0){
            return teamOneDetails;
        }
        else{
            return teamTwoDetails;
        }
    }

    public void chooseBatOrField(TeamDetails teamOneDetails, TeamDetails teamTwoDetails, Match match){
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
