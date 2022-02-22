package com.tekion.cricket.service;

import com.tekion.cricket.bean.PlayerDetails;
import com.tekion.cricket.bean.TeamDetails;
import com.tekion.cricket.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
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

    public TeamDetails initialiseTeamByFile() {
        TeamDetails teamDetails = new TeamDetails();
        System.out.print("Enter team file name : ");
        String teamFileName = getTeamFileName();
        File file = new File("/Users/rk/Downloads/cricket/src/main/java/com/tekion/cricket/teamfile/" + teamFileName.trim());
        List<PlayerDetails> playerDetailsList = new ArrayList<>();
        try(Scanner teamFile = new Scanner(file)){
            if(teamFile.hasNextLine()){
                teamDetails.setTeamName(teamFile.nextLine());
            }
            int numberOfPlayer = 0;
            while(teamFile.hasNextLine()){
                PlayerDetails playerDetails = new PlayerDetails();
                playerDetails.setPlayerCode(numberOfPlayer);
                playerDetails.setPlayerName(teamFile.nextLine());
                playerDetails.setPlayerType(getPlayerTypeFromFile(teamFile.nextLine()));
                playerDetails.setBattingRating(Integer.parseInt(teamFile.nextLine()));
                playerDetails.setBowlingRating(Integer.parseInt(teamFile.nextLine()));
                playerDetailsList.add(playerDetails);
                numberOfPlayer++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
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

    private String getTeamFileName(){
        String teamFileName;
        while(true){
            teamFileName = scanner.next();
            if(teamFileName.equalsIgnoreCase("royals") || teamFileName.equalsIgnoreCase("rhinos")
                    || teamFileName.equalsIgnoreCase("pirates")){
                return teamFileName.toLowerCase();
            }
            else{
                logger.info("\nEnter a valid file name");
            }
        }
    }

    private PlayerDetails.PlayerType getPlayerTypeFromFile(String playerType){
        if (playerType.equalsIgnoreCase("BAT")) {
            return PlayerDetails.PlayerType.BAT;
        }
        else{
            return PlayerDetails.PlayerType.BALL;
        }
    }
}
