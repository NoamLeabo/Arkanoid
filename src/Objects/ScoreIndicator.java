// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import biuoop.DrawSurface;
import RunSystem.*;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    /**
     * The constant M325.
     */
    public static final int M325 = 325;
    /**
     * The constant M17.
     */
    public static final int M17 = 17;
    /**
     * The constant M20.
     */
    public static final int M20 = 20;
    /**
     * The Upper left.
     */
    private final Point upperLeft;
    /**
     * The Upper right.
     */
    private final Point upperRight;
    /**
     * The Downer left.
     */
    private final Point downerLeft;
    /**
     * The Downer right.
     */
    private final Point downerRight;
    private final Rectangle block;
    private final java.awt.Color color;
    private final Counter score;
    private String name;


    /**
     * Instantiates a new Score indicator.
     *
     * @param upperLeft   the upper left of the block
     * @param downerRight the downer right of the block
     * @param color       the color of the block
     * @param score       the score
     * @param name        the name
     */
    public ScoreIndicator(Point upperLeft, Point downerRight,
                          java.awt.Color color, Counter score, String name) {
        Rectangle rectangle = new Rectangle(upperLeft,
                downerRight.getX() - upperLeft.getX(),
                downerRight.getY() - upperLeft.getY());
        this.block = rectangle;
        //same here we get the points' values
        this.upperLeft = block.getUpperLeft();
        this.upperRight =
                new Point(block.getUpperLeft().getX() + block.getWidth(),
                        block.getUpperLeft().getY());
        this.downerLeft = new Point(block.getUpperLeft().getX(),
                block.getUpperLeft().getY() + block.getHeight());
        this.downerRight = new Point(block.getUpperLeft().getX()
                + block.getWidth(), block.getUpperLeft().getY()
                + block.getHeight());
        this.color = color;
        this.score = score;
        this.name = name;
    }

    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param surface the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //we set the block's color
        surface.setColor(this.color);
        //first we fill the block's inside
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
        surface.setColor(Color.black);
        //print the current score
        surface.drawText(200, M17,
                "Your score is: " + this.score.getValue(), 17);
        surface.drawText(450, M17,
                "Level Name: " + this.name, 17);
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
    }
}
