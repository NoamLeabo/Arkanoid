// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Drawer 3.
 */
public class Drawer3 implements Sprite {
    /**
     * The constant DEF.
     */
    public static final int DEF = 0;
    /**
     * The constant M20.
     */
    public static final int M20 = 20;
    /**
     * The constant M780.
     */
    public static final int M780 = 780;
    /**
     * The constant M580.
     */
    public static final int M580 = 580;
    private int col1 = DEF;
    private int col2 = M20;
    private int col3 = 40;
    private int col4 = 60;
    private int col5 = 80;
    private int col6 = 0;
    private int col7 = 10;
    private int col8 = 20;


    /**
     * Draw on.
     * draw the sprite to the screen
     * the background for the third level
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //we set the block's color
        surface.setColor(new Color(DEF, 102, 102));

        //first we fill the block's inside
        surface.fillRectangle(M20, M20, M780, M580);
        //we draw the stand for the fork
        surface.setColor(new Color(96, 96, 96));
        surface.fillRectangle(200, 430, 100, 170);
        surface.setColor(new Color(160, 160, 160));
        surface.fillRectangle(220, 450, 60, 150);

        surface.setColor(new Color(64, 64, 64));
        for (int i = DEF; i < M20; i++) {
            surface.drawLine(180 + i, 290, 240 + i, 430);
        }
        //we draw the spikes
        surface.setColor(new Color(255, 255, DEF));

        //left spike
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(120, 280, 180 + i, 290);
        }
        surface.fillRectangle(120, 240, 10, 40);
        for (int i = DEF; i < 16; i++) {
            surface.drawLine(125, 220, 117 + i, 240);
        }

        //right spike
        for (int i = DEF; i < 10; i++) {
            surface.drawLine(260, 280, 200 - i, 290);
        }
        surface.fillRectangle(250, 240, 10, 40);
        for (int i = DEF; i < 16; i++) {
            surface.drawLine(255, 220, 262 - i, 240);
        }
        //middle spike
        surface.fillRectangle(187, 225, 8, 65);
        for (int i = DEF; i < 12; i++) {
            surface.drawLine(191, 180, 185 + i, 225);
        }
        //lights coming out of the spikes
        surface.setColor(new Color(DEF, 76, 153));
        for (int i = DEF; i < 100; i++) {
            surface.drawLine(191, 180, 100 + 15 * i, M20);
        }
        //the moving flair from the middle spike
        surface.setColor(new Color(DEF, 200, 253));
        surface.drawLine(191, 180, 100 + 15 * col1, M20);
        surface.drawLine(191, 180, 100 + 15 * col2, M20);
        surface.drawLine(191, 180, 100 + 15 * col3, M20);
        surface.drawLine(191, 180, 100 + 15 * col4, M20);
        surface.drawLine(191, 180, 100 + 15 * col5, M20);
        //moving the lights one step forward
        if (col1 == 100) {
            col1 = DEF;
        }
        if (col2 == 100) {
            col2 = DEF;
        }
        if (col3 == 100) {
            col3 = DEF;
        }
        if (col4 == 100) {
            col4 = DEF;
        }
        if (col5 == 100) {
            col5 = DEF;
        }
        ++col1;
        ++col2;
        ++col3;
        ++col4;
        ++col5;
        //drawing the other spike's lights
        surface.setColor(new Color(153, 255, 204));
        for (int i = DEF; i < 30; i++) {
            surface.drawLine(255, 220, M780, -100 + 15 * i);
        }

        surface.setColor(new Color(0, 76, 153));
        surface.drawLine(255, 220, M780, 335 - 15 * col6);
        surface.drawLine(255, 220, M780, 335 - 15 * col7);
        surface.drawLine(255, 220, M780, 335 - 15 * col8);
        if (col6 == 29) {
            col6 = DEF;
        }
        if (col7 == 29) {
            col7 = DEF;
        }
        if (col8 == 29) {
            col8 = DEF;
        }

        surface.setColor(new Color(204, 153, 255));
        for (int i = DEF; i < 30; i++) {
            surface.drawLine(125, 220, -200 + 15 * i, M20);
        }

        surface.setColor(new Color(204, 0, 102));
        surface.drawLine(125, 220, -200 + 15 * col6, M20);
        surface.drawLine(125, 220, -200 + 15 * col7, M20);
        surface.drawLine(125, 220, -200 + 15 * col8, M20);
        if (col6 == 29) {
            col6 = DEF;
        }
        if (col7 == 29) {
            col7 = DEF;
        }
        if (col8 == 29) {
            col8 = DEF;
        }
        ++col6;
        ++col7;
        ++col8;

    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

}
