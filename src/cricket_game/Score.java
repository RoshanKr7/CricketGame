package cricket_game;

import java.util.Random;

public class Score {
    private Random rand = new Random();
    int[] scoreChances = {20, 45, 65, 72, 87, 88, 98, 105};
  public int scoreUtility() {
      int utility = rand.nextInt(106);
      int score = 0;
      while(scoreChances[score] < utility){
          score++;
      }
      return score;
  }
}
