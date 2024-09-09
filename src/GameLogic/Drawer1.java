// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Drawer 1.
 */
public class Drawer1 implements Sprite {

    public static final int M20 = 20;
    public static final int M780 = 780;
    public static final int M580 = 580;
    public static final int DEF = 0;
    public static final int M1 = 1;

    /**
     * Draw on.
     * draw the sprite to the screen
     * the background for the first level
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //we set the block's color
        surface.setColor(Color.BLACK);

        //first we fill the block's inside
        surface.fillRectangle(M20, M20, M780, M580);

        //we draw the outline circles
        surface.setColor(Color.BLUE);
        surface.drawCircle(415, 190, 133);
        surface.drawCircle(415, 190, 132);

        surface.setColor(new Color(DEF, 153, 153));
        surface.drawCircle(415, 190, 130);
        surface.drawCircle(415, 190, 125);
        surface.drawCircle(415, 190, 118);

        surface.setColor(new Color(DEF, 76, 153));
        surface.drawCircle(415, 190, 108);
        surface.drawCircle(415, 190, 96);
        surface.drawCircle(415, 190, 81);

        surface.setColor(new Color(102, 102, 255));
        surface.drawCircle(415, 190, 64);
        surface.drawCircle(415, 190, 60);
        //we draw the lines
        surface.setColor(Color.BLUE);
        surface.drawLine(210, 190, 365, 190);
        surface.drawLine(465, 190, 620, 190);
        surface.drawLine(220, 80, 220, 320);
        surface.drawLine(210, 120, 210, 280);
        surface.drawLine(205, 160, 205, 240);
        surface.drawLine(610, 80, 610, 320);
        surface.drawLine(620, 120, 620, 280);
        surface.drawLine(625, 160, 625, 240);

        //we draw the "laser" lines
        surface.setColor(Color.RED);
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(190, 180, M20, 80 - i);
        }
        surface.setColor(new Color(255, 153, 153));
        for (int i = DEF; i < 4; i++) {
            surface.drawLine(190, 180, M20, 77 - i);
        }
        //shades
        surface.setColor(Color.WHITE);
        for (int i = DEF; i < M1; i++) {
            surface.drawLine(190, 180, M20, 76 - i);
        }
        //we draw the "laser" lines
        surface.setColor(Color.RED);
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(190, 210, M20, 500 - i);
        }
        surface.setColor(new Color(255, 153, 153));
        for (int i = DEF; i < 4; i++) {
            surface.drawLine(190, 210, M20, 497 - i);
        }
        //shades
        surface.setColor(Color.WHITE);
        for (int i = DEF; i < M1; i++) {
            surface.drawLine(190, 210, M20, 497 - i);
        }
        //same thing but the other side
        surface.setColor(Color.RED);
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(640, 180, M780, 120 - i);
        }
        surface.setColor(new Color(255, 153, 153));
        for (int i = DEF; i < 4; i++) {
            surface.drawLine(640, 180, M780, 117 - i);
        }

        surface.setColor(Color.WHITE);
        for (int i = DEF; i < M1; i++) {
            surface.drawLine(640, 180, M780, 116 - i);
        }

        surface.setColor(Color.RED);
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(640, 210, M780, 350 - i);
        }
        surface.setColor(new Color(255, 153, 153));
        for (int i = DEF; i < 4; i++) {
            surface.drawLine(640, 210, M780, 347 - i);
        }

        surface.setColor(Color.WHITE);
        for (int i = DEF; i < M1; i++) {
            surface.drawLine(640, 210, M780, 348 - i);
        }
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

}
