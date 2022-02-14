package com.tekion.cricket.service;

import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TeamService {
    private Scanner scanner = new Scanner(System.in);
    private Logger logger = Logger.getLogger(TeamService.class.getName());

    public TeamDetails initialiseTeam(){
        TeamDetails teamDetails = new TeamDetails();
        System.out.print("Enter team name : ");
        teamDetails.setTeamName(getTeamName());
        int numberOfPlayer = 0;
        List<PlayerDetails> playerDetailsList = new ArrayList<>();
        while(numberOfPlayer <= Constants.NUMBER_WICKETS){
            System.out.println("Enter Player " + (numberOfPlayer+1) + " Detail : ");
            doInitialisePlayer(playerDetailsList, numberOfPlayer);
            numberOfPlayer++;
        }
        teamDetails.setPlayersDetails(playerDetailsList);
        return teamDetails;
    }

    private String getTeamName(){
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

    private void doInitialisePlayer(List<PlayerDetails> playerDetailsList, int numberOfPlayer){
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setPlayerCode(numberOfPlayer);
        System.out.print("Name : ");
        playerDetails.setPlayerName(scanner.next());
        System.out.print("\nType (Bat/Ball) : ");
        playerDetails.setPlayerType(getPlayerType());
        System.out.print("\nBatting Rating (Out of 10) : ");
        playerDetails.setBattingRating(scanner.nextInt());
        System.out.print("\nBowling Rating (Out of 10) : ");
        playerDetails.setBowlingRating(scanner.nextInt());
        playerDetailsList.add(playerDetails);
    }

    private PlayerDetails.PlayerType getPlayerType(){
        String playerType;
        while (true) {
            playerType = scanner.next();
            if (playerType.equalsIgnoreCase("BAT")) {
                return PlayerDetails.PlayerType.BAT;
            }
            else if(playerType.equalsIgnoreCase("BALL")){
                return PlayerDetails.PlayerType.BALL;
            }
            logger.info("Please Enter correct Player type");
        }
    }
}
