// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Drawer 2.
 */
public class Drawer2 implements Sprite {

    public static final int DEF = 0;
    public static final int M20 = 20;
    public static final int M780 = 780;
    public static final int M580 = 580;
    private int place1 = 0;
    private boolean change1 = false;
    private int place2 = 0;
    private boolean change2 = false;

    /**
     * Draw on.
     * draw the sprite to the screen
     * the background for the second level
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //we set the block's color
        surface.setColor(new Color(204, 255, 255));

        //first we fill the block's inside
        surface.fillRectangle(M20, M20, M780, M580);

        //clouds
        surface.setColor(Color.WHITE);
        surface.fillCircle(560 + place1, 140, 40);
        surface.fillCircle(620 + place1, 120, 50);
        surface.fillCircle(680 + place1, 140, 40);
        surface.fillCircle(710 + place1, 140, M20);
        if (!change1) {
            ++place1;
        }
        if (place1 > 150) {
            change1 = true;
        }
        if (change1) {
            --place1;
            if (place1 < -150) {
                change1 = false;
            }
        }

        surface.fillCircle(300 + place2, 120, 40);
        surface.fillCircle(360 + place2, 140, 50);
        surface.fillCircle(420 + place2, 130, 40);
        surface.fillCircle(450 + place2, 120, M20);
        if (!change2) {
            --place2;
        }
        if (place2 < -200) {
            change2 = true;
        }
        if (change2) {
            ++place2;
            if (place2 > 200) {
                change2 = false;
            }
        }

        surface.fillCircle(200 - place2, 120, 40);
        surface.fillCircle(260 - place2, 145, 50);
        surface.fillCircle(300 - place2, 140, 40);
        surface.fillCircle(330 - place2, 110, M20);


        //we draw the lines that are coming out from the sun
        surface.setColor(new Color(255, 255, DEF));
        for (int i = DEF; i < 100; i++) {
            surface.drawLine(200, 200, M20 + 15 * i, 300);
        }
        //we draw the sun with shades
        surface.setColor(new Color(255, 255, 153));
        surface.fillCircle(200, 200, 70);
        surface.setColor(new Color(255, 255, DEF));
        //we draw the outline
        surface.fillCircle(200, 200, 50);
        surface.setColor(new Color(255, 255, 204));
        surface.fillCircle(200, 200, 40);

    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

}
