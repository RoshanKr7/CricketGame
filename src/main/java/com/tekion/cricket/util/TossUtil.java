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

    public String toss(TeamDetails teamOneDetails, TeamDetails teamTwoDetails){
        int tossResult = random.nextInt(2);
        if(tossResult == 0){
            return teamOneDetails.getTeamName();
        }
        else{
            return teamTwoDetails.getTeamName();
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
            if(match.getTossWinnerName().equals(teamOneDetails.getTeamName())){
                match.setBattingFirstTeam(teamOneDetails);
                match.setBowlingFirstTeam(teamTwoDetails);
            }
            else{
                match.setBattingFirstTeam(teamTwoDetails);
                match.setBowlingFirstTeam(teamOneDetails);
            }
        }
        else{
            if(match.getTossWinnerName().equals(teamOneDetails.getTeamName())){
                match.setBattingFirstTeam(teamTwoDetails);
                match.setBowlingFirstTeam(teamOneDetails);
            }
            else{
                match.setBattingFirstTeam(teamOneDetails);
                match.setBowlingFirstTeam(teamTwoDetails);
            }
        }
    }
}
