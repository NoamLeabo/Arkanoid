// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game level 1.
 */
public class GameLevel1 implements LevelInformation {

    /**
     * The constant DEF.
     */
    public static final int DEF = 0;
    /**
     * The constant R.
     */
    public static final int R = 5;
    /**
     * The constant PADDLE_WIDTH.
     */
    public static final int PADDLE_WIDTH = 50;
    /**
     * The constant PADDLE_START.
     */
    public static final int PADDLE_START = 390;
    private List<Ball> balls;
    private List<Block> blocks;
    private int paddleSpeed;
    private int paddleWidth;
    private String name;
    private Sprite background;
    private int paddleStart;

    /**
     * Instantiates a new Game level 1.
     */
    public GameLevel1() {
        //we create the level's ball
        Ball ball = new Ball(new Point(415, 550), R, Color.WHITE);
        //we set the ball's velocity
        ball.setVelocity(DEF, -R);
        //we keep it in a list
        List<Ball> balls = new ArrayList<>();
        balls.add(ball);
        this.balls = balls;
        //we set the paddle's values
        this.paddleSpeed = R;
        this.paddleWidth = PADDLE_WIDTH;
        this.paddleStart = PADDLE_START;
        //the name of the level
        this.name = "Star Wars";
        //we create the level's block
        Block block1 = new Block(new Point(385, 160), new Point(445,
                220), Color.RED);
        //we keep it in a list
        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        this.blocks = blocks;
        //we get the level's background
        Drawer1 drawer = new Drawer1();
        this.background = drawer;
    }
    /**
     * Number of balls int.
     *
     * @return the numberOfBalls
     */
    public int numberOfBalls() {
        return this.balls.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = DEF; i < balls.size(); i++) {
            velocities.add(this.balls.get(i).getVelocity());
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.name;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    @Override
    public Color paddleColor() {
        return new Color(127, DEF, 255);
    }
    /**
     * Gets paddle start.
     *
     * @return the paddle starting point
     */
    public int getPaddleStart() {
        return this.paddleStart;
    }


}
