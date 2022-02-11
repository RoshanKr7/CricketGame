package com.tekion.cricket.service;

import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TeamService {
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(TeamService.class.getName());

    public static TeamDetails initialiseTeam(){
        TeamDetails teamDetails = new TeamDetails();
        System.out.print("Enter team name : ");
        teamDetails.setTeamName(getTeamName());
        int numberOfPlayer = 0;
        List<PlayerDetails> playerDetailsList = new ArrayList<>();
        while(numberOfPlayer <= Constants.NUMBER_WICKETS){
            PlayerDetails playerDetails = new PlayerDetails();
            System.out.println("Enter Player " + (numberOfPlayer+1) + " Detail : ");
            System.out.print("Name : ");
            playerDetails.setPlayerName(scanner.next());
            System.out.print("\nType (Bat/Ball) : ");
            playerDetails.setPlayerType(scanner.next());
            System.out.print("\nBatting Rating (Out of 10) : ");
            playerDetails.setBattingRating(scanner.nextInt());
            System.out.print("\nBowling Rating (Out of 10) : ");
            playerDetails.setBowlingRating(scanner.nextInt());
            playerDetailsList.add(playerDetails);
            numberOfPlayer++;
        }
        teamDetails.setPlayersDetails(playerDetailsList);
        return teamDetails;
    }

    private static String getTeamName(){
        String teamName;
        while (true) {
            teamName = scanner.next();
            if (teamName.equals("")) {
                logger.info("Please Enter something in Name");
            }
            else{
                break;
            }
        }
        return teamName;
    }
}
