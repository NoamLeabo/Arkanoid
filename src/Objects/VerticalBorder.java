// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import RunSystem.*;
import java.awt.Color;
import java.util.List;

/**
 * The type Vertical border.
 */
public class VerticalBorder extends Block implements Border {
    public static final int M0 = 0;
    public static final int M70 = 70;
    public static final int ADD_30 = 30;
    private final List<Ball> balls;

    /**
     * Instantiates a new Graphics.Block from 2 opposite dots .
     *
     * @param upperLeft   the upperLeft point of the block
     * @param downerRight the downerRight point of the block
     * @param color       the color of the block
     * @param balls       the balls of the game
     */
    public VerticalBorder(Point upperLeft, Point downerRight,
                          Color color, List<Ball> balls) {
        super(upperLeft, downerRight, color);
        this.balls = balls;

    }

    /**
     * Crosses the border.
     * returning the ball to the game area in case it got into the block
     */
    public void crossesTheBorder() {
        for (int i = M0; i < balls.size(); i++) {
            //if the ball is inside the block
            if (balls.get(i).inZone((int) getDownerRight().getX(),
                    (int) getDownerRight().getY(),
                    (int) getUpperLeft().getX(),
                    (int) getUpperLeft().getY())) {
                //if it's in the left border, fix accordingly
                int add30 = ADD_30;
                if (balls.get(i).getX() <= M70) {
                    balls.get(i).setX(getDownerRight().getX() + add30);
                } else {
                //if it's in the right border, fix accordingly
                    balls.get(i).setX(getUpperLeft().getX() - add30);
                }
            }
        }
    }

    @Override
    public void timePassed() {
        crossesTheBorder();
    }

    /**
     * Stop.
     */
    public void stop() {
    }
}
