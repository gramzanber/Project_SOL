
package view.gameobjects;

/**
 *
 * @author Sam
 */
public class Score {
    public static int currentScore = 0;
    
    public int getScore(){
        return currentScore;
    }
    
    public static void setScore(int s){
        currentScore += s;
        Hero.setScore(currentScore);
    }
}
