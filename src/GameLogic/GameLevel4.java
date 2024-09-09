// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Game level 3.
 */
public class GameLevel4 implements LevelInformation {

    public static final int GET_PADDLE_START = 350;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_SPEED = 9;
    public static final int DEF = 0;
    public static final int R = 5;
    private List<Ball> balls;
    private List<Block> blocks;
    private int paddleSpeed;
    private int paddleWidth;
    private int getPaddleStart;
    private String name;
    private Sprite background;

    /**
     * Instantiates a new Game level 3.
     */
    public GameLevel4() {
        Random random = new Random();
        //we create the level's balls
        List<Ball> balls = new ArrayList<>();
        for (int i = DEF; i < 2; i++) {
            Ball ball = new Ball(new Point(415, 550), R,
                    Color.WHITE);
            //we set their velocities
            ball.setVelocity(-4 + 8 * i, -4);
            ball.gameStatus();
            balls.add(ball);
        }
        //we keep them in a list
        this.balls = balls;
        //we set the paddle's values
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.getPaddleStart = GET_PADDLE_START;
        //the name of he level
        this.name = "Random";
        //we keep the level's colors
        Color[] colors = new Color[20];
        colors[DEF] = new Color(255, DEF, DEF);
        colors[1] = Color.GRAY;
        colors[2] = new Color(DEF, 153, 153);
        colors[3] = new Color(102, 102, 255);
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
        //we create the level's blocks
        List<Block> blocks = new ArrayList<>();
        int linesNum = random.nextInt(6) + 5;
        int ColNum = random.nextInt(5) + 10;
        for (int i = DEF; i < linesNum; i++) {
            for (int j = DEF; j < ColNum; j++) {
                Point p1 = new Point(700 - j * 50 - 20, 160 + i * 20);
                Point p2 =
                        new Point(750 - j * 50 - 20, 180 + i * 20);
                int skip = random.nextInt(5) + 1;
                if (skip == 3) {
                    continue;
                }
                int r = random.nextInt(5) + 1;
                if (r == 1) {
                    BlockBal block = new BlockBal(p1, p2,
                            colors[random.nextInt(2) +2]);
                    blocks.add(block);
                } else if (r == 2) {
                    BlockPad block = new BlockPad(p1, p2,
                            colors[random.nextInt(2) +2 ]);
                    blocks.add(block);
                } else if (r == 3) {
                    Block block = new Block(p1, p2,
                            colors[1]);
                    blocks.add(block);
                    block.setHits(random.nextInt(3) + 2);
                    block.setSecColor(Color.red);
                } else {
                    Block block = new Block(p1, p2,
                            colors[random.nextInt(2) +2 ]);
                    blocks.add(block);
                }
            }
        }

        //we keep them in a list
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
            return Color.lightGray;
    }

    @Override
    public int getPaddleStart() {
        return this.getPaddleStart;
    }
}
