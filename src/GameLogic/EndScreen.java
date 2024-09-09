// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.swing.*;
import java.awt.*;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    /**
     * The constant M2.
     */
    public static final int M2 = 2;
    /**
     * The constant X.
     */
    public static final int X = 10;
    /**
     * The constant FONT.
     */
    public static final int FONT = 32;
    private KeyboardSensor keyboard;
    private boolean stop;
    private ScoreTrackingListener scoreTrackingListener;
    private boolean win;

    /**
     * Instantiates a new End screen.
     *
     * @param k                     the KeyboardSensor
     * @param scoreTrackingListener the score tracking listener
     * @param win                   the win indicator
     */
    public EndScreen(KeyboardSensor k,
                     ScoreTrackingListener scoreTrackingListener,
                     boolean win) {
        this.keyboard = k;
        this.stop = false;
        this.scoreTrackingListener = scoreTrackingListener;
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //if a win is occurred show an end screen of a win
        if (win) {
            d.setColor(Color.yellow);
            d.fillRectangle(0,0,800,600);
            d.setColor(Color.orange);
            d.fillRectangle(50,50,700,500);


            // Use ClassLoader to load the "sad.jpg" image from the resources directory
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            java.net.URL imageURL = classLoader.getResource("sme.jpg");
            if (imageURL != null) {
                Image image = new ImageIcon(imageURL).getImage();
                d.drawImage(35, 100, image);
            }

            d.setColor(Color.BLACK);
            d.drawText(200, 470,
                    "You Won! Your score is "
                            + this.scoreTrackingListener.getCurrentScore()
                            .getValue(), FONT);
        } else {
            //if a lost is occurred show an end screen of a lost
            d.setColor(Color.BLUE);
            d.fillRectangle(0,0,800,600);
            d.setColor(Color.LIGHT_GRAY);
            d.fillRectangle(50,50,700,500);



            // Use ClassLoader to load the "sad.jpg" image from the resources directory
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            java.net.URL imageURL = classLoader.getResource("sad.jpg");

            if (imageURL != null) {
                Image image = new ImageIcon(imageURL).getImage();
                d.drawImage(0, -430, image);
            }


            d.setColor(Color.BLACK);
            d.drawText(200, 575,
                    "Game Over. Your score is "
                            + this.scoreTrackingListener.getCurrentScore()
                            .getValue(), FONT);
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean whether it shouldStop or not
     */
    public boolean shouldStop() {
        return this.stop;
    }
}