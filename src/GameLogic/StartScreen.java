// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The type End screen.
 */
public class StartScreen implements Animation {

    private boolean stop;
    public StartScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        java.net.URL imageURL = classLoader.getResource("Welcome to " +
                "Arknoid.png");

        if (imageURL != null) {
            Image image = new ImageIcon(imageURL).getImage();
            d.drawImage(0, 0, image);
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