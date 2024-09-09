package Objects;
import Interfaces.*;
import biuoop.DrawSurface;

import java.awt.*;

public class Heart implements Sprite{
    private int num;

    public Heart(int num){
        this.num = num;
    }
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        //printing the hearts
        for (int i = 0; i < num; i++) {
            d.setColor(Color.RED);
            d.fillCircle(40 + i * 20, 50, 5);
            d.fillCircle(48 + i * 20, 50, 5);
            d.drawLine(35 + i * 20, 50, 44 + i * 20, 60);
            d.drawLine(36 + i * 20, 51, 44 + i * 20, 59);
            d.drawLine(37 + i * 20, 52, 44 + i * 20, 58);
            d.drawLine(38 + i * 20, 53, 44 + i * 20, 57);
            d.drawLine(39 + i * 20, 54, 44 + i * 20, 56);

            d.drawLine(53 + i * 20, 50, 44 + i * 20, 60);
            d.drawLine(52 + i * 20, 51, 44 + i * 20, 59);
            d.drawLine(51 + i * 20, 52, 44 + i * 20, 58);
            d.drawLine(50 + i * 20, 53, 44 + i * 20, 57);
            d.drawLine(49 + i * 20, 54, 44 + i * 20, 56);

            d.fillCircle(44 + i * 20, 54, 4);
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
