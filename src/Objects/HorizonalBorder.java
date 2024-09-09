// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import java.awt.Color;
import java.util.List;


/**
 * The type Horizonal border.
 */
public class HorizonalBorder extends Block implements Border {
    public static final int M30 = 30;
    public static final int M70 = 70;
    public static final int M0 = 0;
    private final List<Ball> balls;

    /**
     * Instantiates a new Graphics.Block from 2 opposite dots .
     *
     * @param upperLeft   the upperLeft point of the block
     * @param downerRight the downerRight point of the block
     * @param color       the color of the block
     * @param balls       the balls of the game
     */
    public HorizonalBorder(Point upperLeft, Point downerRight,
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
                //if it's in the upper border, fix accordingly
                if (balls.get(i).getY() <= M70) {
                    balls.get(i).setY(getDownerRight().getY() + M30);
                } else {
                //if it's in the bottom border, fix accordingly
                    balls.get(i).setY(getUpperRight().getY() - M30);
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
