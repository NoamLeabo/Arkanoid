// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * The constant X.
     */
    public static final int X = 10;
    /**
     * The constant FONT.
     */
    public static final int FONT = 32;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0 ,0, 800,600);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        java.net.URL imageURL = classLoader.getResource("pause.jpg");

        if (imageURL != null) {
            Image image = new ImageIcon(imageURL).getImage();
            d.drawImage(106, -50, image);
        }


        d.setColor(Color.black);
        //we do on frame of the pause screen
        d.drawText(160, 520,
                "paused -- press space to continue",
                FONT);
        d.setColor(Color.lightGray);
        d.fillRectangle(0 ,0, 800,40);
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