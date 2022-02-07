package cricket_game;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Random;

class Cricket {
    static String[] player = new String[2];
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private static void result(int scoreTeam1, int scoreTeam2, int wicketFallen, int numberOfBalls, int ballsThrown){
        if(scoreTeam1 > scoreTeam2){
            System.out.println("Team " + player[0] +" has won the Match by " + (scoreTeam1-scoreTeam2) + " Runs.");
        }
        else if(scoreTeam1 < scoreTeam2){
            System.out.println("Team "+ player[1] +" has won the Match by " + (10-wicketFallen) + " Wickets and " + (numberOfBalls-ballsThrown) + " Balls.");
        }
        else{
            System.out.println("!!Draw!!");
        }
    }

    private static int toss(String[] playerName){
        Logger logger = Logger.getLogger(Cricket.class.getName());
        int tossResult = rand.nextInt(2);
        int choice = 0;
        logger.info("Team " + playerName[tossResult] + " Won the Toss");
        while(true){
            System.out.println("Press 0 for Bat First \nPress 1 for Field First");
            choice = scanner.nextInt();
            if(choice == 0 || choice == 1){
                break;
            }
            else{
                System.out.println("!! Please Choose a valid input !!");
            }
        }
        return (choice ^ tossResult);
    }

    public static void main(String[] args){
        ScoreBoard battingFirst = new ScoreBoard();
        ScoreBoard battingSecond = new ScoreBoard();
        StartInning startInning = new StartInning();
        Logger logger = Logger.getLogger(Cricket.class.getName());

        String[] playerName = new String[2];
        logger.info("Enter Player 1 Name : ");
        playerName[0] = scanner.next();
        logger.info("Enter Player 2 Name : ");
        playerName[1] = scanner.next();

        int battingFirstTeam = toss(playerName);

        if(battingFirstTeam==1){
            player[0] = playerName[1];
            player[1] = playerName[0];
        }
        else{
            player[0] = playerName[0];
            player[1] = playerName[1];
        }

        logger.info("No. of Overs in the Match : ");
        int overs = scanner.nextInt();
        battingFirst.setNumberOfBalls(overs);
        battingSecond.setNumberOfBalls(overs);
        int wickets = 10;

        System.out.println("Team " + player[0] + " is going to start their innings");
        wait(3000);
        startInning.startFirstInning(battingFirst, wickets);

        System.out.println("Team " + player[0] + " Innings Over \n" + "Final Score : " + battingFirst.getTeamScore() +"/" + battingFirst.getWicketFallen());
        wait(2000);
        System.out.println();

        System.out.println("Team " + player[1] + " is going to start their innings");
        wait(3000);
        startInning.startSecondInning(battingFirst, battingSecond, wickets);


        System.out.println("Team " + player[1] + " Innings Over \n" + "Final Score : " + battingSecond.getTeamScore() +"/" + battingSecond.getWicketFallen());
        wait(2000);
        System.out.println();

        result(battingFirst.getTeamScore(), battingSecond.getTeamScore(), battingSecond.getWicketFallen(), battingSecond.getNumberOfBalls(), battingSecond.getBallsThrown());

    }
}
