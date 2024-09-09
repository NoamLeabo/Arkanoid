// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Game level 2.
 */
public class GameLevel2 implements LevelInformation {

    public static final int R = 5;
    public static final int DEF = 0;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 600;
    public static final int GET_PADDLE_START = 95;
    private List<Ball> balls;
    private List<Block> blocks;
    private int paddleSpeed;
    private int paddleWidth;
    private int getPaddleStart;
    private String name;
    private Sprite background;


    /**
     * Instantiates a new Game level 2.
     */
    public GameLevel2() {
        List<Ball> balls = new ArrayList<>();
        //we create the level's balls
        for (int i = DEF; i < 10; i++) {
            Ball ball = new Ball(new Point(415, 550), R,
                    new Color(255, 204, 229));
            //we set the balls' velocities
            ball.setVelocity(Velocity.fromAngleAndSpeed(-64 + 14 * i, 4));
            balls.add(ball);
        }
        //we keep them in a list
        this.balls = balls;
        //we set the paddle's info
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.getPaddleStart = GET_PADDLE_START;
        //the name of the level
        this.name = "Sky Rainbow";
        //we keep the blocks' colors in an array
        Color[] colors = new Color[20];
        colors[DEF] = new Color(255, DEF, DEF);
        colors[1] = new Color(255, DEF, DEF);
        colors[PADDLE_SPEED] = new Color(255, 128, DEF);
        colors[3] = new Color(255, 128, DEF);
        colors[4] = new Color(255, 255, DEF);
        colors[R] = new Color(255, 255, DEF);
        colors[6] = new Color(128, 255, DEF);
        colors[7] = new Color(128, 255, DEF);
        colors[8] = new Color(DEF, 255, DEF);
        colors[9] = new Color(DEF, 255, DEF);
        colors[10] = new Color(DEF, 255, 128);
        colors[11] = new Color(DEF, 255, 128);
        colors[12] = new Color(DEF, 255, 255);
        colors[13] = new Color(DEF, 255, 255);
        colors[14] = new Color(DEF, 128, 255);
        colors[15] = new Color(DEF, 128, 255);
        colors[16] = new Color(DEF, DEF, 255);
        colors[17] = new Color(DEF, DEF, 255);
        colors[18] = new Color(127, DEF, 255);
        colors[19] = new Color(127, DEF, 255);
        //we creat the level's blocks with the right colors
        List<Block> blocks = new ArrayList<>();
        for (int i = DEF; i < 20; i++) {
            Point p1 = new Point(20 + i * 38, 300);
            Point p2 = new Point(58 + i * 38, 320);
            Random random = new Random();
            int r = random.nextInt(5) + 1;
            if (r == 1) {
                BlockBal block = new BlockBal(p1, p2, colors[i]);
                blocks.add(block);
            } else if (r == 2) {
                BlockPad block = new BlockPad(p1, p2, colors[i]);
                blocks.add(block);
            } else if (r == 3) {
                Block block = new Block(p1, p2,
                        Color.gray);
                blocks.add(block);
                block.setHits(random.nextInt(2) + 2);
                block.setSecColor(colors[i]);
            } else {
                Block block = new Block(p1, p2, colors[i]);
                blocks.add(block);
            }

            //Block block = new Block(new Point(20 + i * 38, 300),
            //        new Point(58 + i * 38,
          //                  320), colors[i]);
          //  blocks.add(block);
        }
        //we keep them in a list
        this.blocks = blocks;
        //we get the level's background
        Drawer2 drawer = new Drawer2();
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
        return new Color(255, 153, 255);
    }
    /**
     * Gets paddle start.
     *
     * @return the paddle starting point
     */
    @Override
    public int getPaddleStart() {
        return this.getPaddleStart;
    }
}
