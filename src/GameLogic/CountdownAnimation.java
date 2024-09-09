// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sprite back;
    private boolean shouldStop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the num to count from
     * @param gameScreen   the game screen
     * @param back         the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, Sprite back) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.shouldStop = false;
        this.back = back;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //if there are no more seconds to count we stop
        if (this.numOfSeconds == 0) {
            this.shouldStop = true;
        }
        //we print the drawing to be on the bachground
        this.back.drawOn(d);
        this.gameScreen.drawAllOn(d);
        //boring stuff of sketching the animation
        d.setColor(new Color(0, 102, 204));
        d.fillRectangle(348, 404, 118, 118);
        d.setColor(new Color(153, 255, 153));
        d.fillRectangle(350, 406, 114, 114);
        d.setColor(new Color(153, 0, 76));
        d.fillRectangle(352, 408, 110, 110);

        d.setColor(new Color(192, 192, 192));
        d.fillRectangle(357, 413, 100, 100);
        //printing the seconds
        d.setColor(Color.WHITE);
        d.drawText(380, 500, String.valueOf(this.countFrom), 100);
        d.setColor(Color.BLACK);
        d.drawText(383, 497, String.valueOf(this.countFrom), 90);
        Sleeper sleeper = new Sleeper();
        //we hold the animation so we'll see the numbers of seconds
        if (numOfSeconds <= 2) {
            sleeper.sleepFor(750);
        }
        //updating the values
        this.numOfSeconds--;
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}