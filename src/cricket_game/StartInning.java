package cricket_game;

public class StartInning {
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

    public void startFirstInning(ScoreBoard battingFirst, int wickets){
        Score score = new Score();
        while(battingFirst.getWicketFallen() < wickets && battingFirst.getBallsThrown() < battingFirst.getNumberOfBalls()){
            battingFirst.setBallsThrown();
            int temp;
            temp = score.scoreUtility();
            if(temp == 7){
                battingFirst.setWicketFallen();
                System.out.println("Wicket Number " + battingFirst.getWicketFallen() + " has Fallen");
                System.out.println("Partnership for Wicket "+ battingFirst.getWicketFallen()+ " is " + battingFirst.getPartnerships());
                wait(2000);
            }
            else{
                battingFirst.setPartnerships(temp);
                battingFirst.setTeamScore(temp);
            }
            if((battingFirst.getBallsThrown()%6) == 0){
                System.out.println("After Over "+ (battingFirst.getBallsThrown()/6) + " : " + battingFirst.getTeamScore() +"/" + battingFirst.getWicketFallen());
                wait(1000);
            }
        }
    }

    public void startSecondInning(ScoreBoard battingFirst, ScoreBoard battingSecond, int wickets){
        Score score = new Score();
        while(battingSecond.getWicketFallen() < wickets && battingSecond.getBallsThrown() < battingSecond.getNumberOfBalls()){
            battingSecond.setBallsThrown();
            int temp;
            temp = score.scoreUtility();
            if(temp == 7){
                battingSecond.setWicketFallen();
                System.out.println("Wicket Number " + battingSecond.getWicketFallen() + " has Fallen");
                System.out.println("Partnership for Wicket "+ battingSecond.getWicketFallen()+ " is " + battingSecond.getPartnerships());
                wait(2000);
            }
            else{
                battingSecond.setPartnerships(temp);
                battingSecond.setTeamScore(temp);
                if(battingSecond.getTeamScore() > battingFirst.getTeamScore()){
                    break;
                }
            }
            if((battingSecond.getBallsThrown()%6) == 0){
                System.out.println("After Over "+ (battingSecond.getBallsThrown()/6) + " : " + battingSecond.getTeamScore() +"/" + battingSecond.getWicketFallen());
                wait(1000);
            }

        }
    }
}
