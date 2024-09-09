// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    /**
     * The constant SCORE.
     */
    public static final int SCORE = 5;
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    /**
     * The constant M780.
     */
    public static final int M780 = 780;
    /**
     * The constant M20.
     */
    public static final int M20 = 20;
    /**
     * The constant M580.
     */
    public static final int M580 = 580;
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Graphics.Graphics.Ball.Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //making sure we didn't hit a border
        if (!beingHit.getUpperLeft().equals(new Point(M0, M0))
                && !beingHit.getUpperLeft().equals(new Point(
                        M780, M20))
                && !beingHit.getUpperLeft().equals(new Point(
                        M0, M580))) {
            //updating the score
            this.currentScore.increase(SCORE);
        }
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}