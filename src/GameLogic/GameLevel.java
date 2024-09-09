// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

import java.util.List;


/**
 * The type Game.Game.
 */
public class GameLevel implements Animation {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    /**
     * The constant M780.
     */
    public static final int M780 = 780;
    /**
     * The constant M20.
     */
    public static final int M20 = 20;
    /**
     * The constant M580.
     */
    public static final int M580 = 580;
    /**
     * The constant M6.
     */
    public static final int M6 = 6;
    /**
     * The constant M1.
     */
    public static final int M1 = 1;
    /**
     * The constant M4.
     */
    public static final int M4 = 4;
    /**
     * The constant M3.
     */
    public static final int M3 = 3;
    /**
     * The constant M2.
     */
    public static final int M2 = 2;
    /**
     * The constant M5.
     */
    public static final int M5 = 5;
    /**
     * The constant M12.
     */
    public static final int M12 = 12;
    /**
     * The constant M750.
     */
    public static final int M750 = 750;
    /**
     * The constant M50.
     */
    public static final int M50 = 50;
    /**
     * The constant M120.
     */
    public static final int M120 = 120;
    /**
     * The constant M140.
     */
    public static final int M140 = 140;
    /**
     * The constant M400.
     */
    public static final int M400 = 400;
    /**
     * The constant M500.
     */
    public static final int M500 = 500;
    /**
     * The constant M60.
     */
    public static final int M60 = 60;
    /**
     * The constant M1000.
     */
    public static final int M1000 = 1000;
    /**
     * The constant M100.
     */
    public static final int M100 = 100;
    /**
     * The constant M45.
     */
    public static final int M45 = 45;
    /**
     * The constant M67.
     */
    public static final int M67 = 67;
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    /**
     * The constant M200.
     */
    public static final int M200 = 200;
    /**
     * The constant M505.
     */
    public static final int M505 = 505;
    /**
     * The constant M330.
     */
    public static final int M330 = 330;
    /**
     * The constant M240.
     */
    public static final int M240 = 240;
    /**
     * The constant FINISH_TIME.
     */
    public static final int FINISH_TIME = 200;
    /**
     * The constant M40.
     */
    public static final int M40 = 40;
    /**
     * The constant M44.
     */
    public static final int M44 = 44;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final biuoop.GUI gui;
    private List<HitListener> hitListeners;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean shouldStop;
    private List<Ball> balls;
    private List<Block> blocks;
    private String name;
    private Sprite background;
    private boolean win;
    private boolean lost;
    private LevelInformation info;
    private ScoreTrackingListener scoreTrackingListener;
    private IconRemover iconRemover;
    /**
     * Instantiates a new Game.Game.
     * we set the given level information
     *
     * @param information           the level information
     * @param gui                   the gui
     * @param ar                    the AnimationRunner
     * @param scoreTrackingListener the score tracking listener
     */
    public GameLevel(LevelInformation information, GUI gui,
                     AnimationRunner ar,
                     ScoreTrackingListener scoreTrackingListener) {
        this.blocks = new ArrayList<>();
        //we set a new list of the sprites of the game
        List<Sprite> spriteList = new ArrayList<>();
        this.sprites = new SpriteCollection(spriteList);

        //we set a new list of the Collidable items of the game
        List<Collidable> collidableList = new ArrayList<>();
        this.environment = new GameEnvironment(collidableList);
        //we store the level info
        this.gui = gui;
        this.hitListeners = new ArrayList<>();
        this.runner = ar;
        this.win = false;
        this.lost = false;
        this.info = information;
        //initialize counters
        int initialNum = M0;
        //counter for blocks
        this.blocksCounter = new Counter(initialNum);
        //counter for balls
        this.ballsCounter = new Counter(initialNum);
        //counter of game score
        this.scoreTrackingListener = scoreTrackingListener;
        this.name = info.levelName();
    }

    /**
     * Add collidable.
     * Add collidable to the game's list
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     * Add sprite to the game's list
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Graphics of the level
     * and add them to the game.
     */
    public void initialize() {

        IconRemover iconRemover = new IconRemover(this);
        this.iconRemover = iconRemover;

        //creat a block remover
        BlockRemover blockRemover =
                new BlockRemover(this, this.blocksCounter,
                        this.iconRemover, this.environment);
        //add the block remover to the hit listeners
        this.hitListeners.add(blockRemover);

        this.blocks = info.blocks();
        //"connecting" the blocks to the game
        for (int i = M0; i < blocks.size(); i++) {
            //add the block to the game
            blocks.get(i).addToGame(this);
            //add his remover
            blocks.get(i).addHitListener(blockRemover);
            //add the score tracker
            blocks.get(i).addHitListener(scoreTrackingListener);
            //update the number of block in the game
            this.blocksCounter.increase(M1);
        }
        //get the level's background
        this.background = info.getBackground();

        //creat a ball remover
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        //add it to the hit listeners
        this.hitListeners.add(ballRemover);

        //we get the level's balls
        this.balls = info.getBalls();

        BallAdder ballAdder = new BallAdder(this, ballsCounter,
                this.balls, environment, ballRemover);
        //add it to the hit listeners
        this.hitListeners.add(ballAdder);

        for (int i = 0; i < info.numberOfBalls(); i++) {
            //add the listener to the ball
            this.balls.get(i).addHitListener(ballRemover);
            //we connect the ball to the game
            this.balls.get(i).setGameEnvironment(this.environment);
            //we update the num of balls
            this.ballsCounter.increase(1);
            //we add it to the game
            this.balls.get(i).addToGame(this);
        }

        //we creat the game's paddle
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, balls, info.paddleSpeed(),
                info.paddleWidth(), info.paddleColor(),
                info.getPaddleStart(), this.iconRemover, ballAdder);
        //we add it to the game
        paddle.addToGame(this);
        //we creat the borders' points

        //borders corners
        Point p11 = new Point(M0, M0 + M20);
        Point p12 = new Point(WIDTH, M40);
        Point p21 = new Point(M780, M20);
        Point p22 = new Point(WIDTH, HEIGHT);
        Point p31 = new Point(M0, M580 + M20);
        Point p32 = new Point(M780, HEIGHT + M20);
        Point p13 = new Point(M20, M580 + M20);
        //top
        HorizonalBorder border1 = new HorizonalBorder(p11, p12,
                Color.gray, balls);
        //right
        VerticalBorder border2 =
                new VerticalBorder(p21, p22, Color.gray, balls);
        //bottom
        HorizonalBorder border3 = new HorizonalBorder(p31, p32,
                Color.BLACK, balls);
        //add the ball remover
        border3.addHitListener(ballRemover);
        //left
        VerticalBorder border4 =
                new VerticalBorder(p11, p13, Color.gray, balls);

        //keeping the borders in an array
        Border[] borders = new Border[M4];
        borders[M0] = border1;
        borders[M1] = border1;
        borders[M2] = border1;
        borders[M3] = border1;

        //add the borders as block to the game
        border1.addToGame(this);
        border2.addToGame(this);
        border3.addToGame(this);
        border4.addToGame(this);

        //corners of the score rectangle
        Point p4 = new Point(M0, M0);
        Point p5 = new Point(WIDTH, M20);
        //creat and add to the game the score rectangle
        ScoreIndicator scoreIndicator = new ScoreIndicator(p4, p5,
                Color.WHITE, scoreTrackingListener.getCurrentScore(),
                this.info.levelName());
        this.addSprite(scoreIndicator);
    }

    /**
     * Remove collidable.
     *
     * @param c the interfaces.Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the interfaces.Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Run.
     * start the animation loop.
     */
    public void run() {
        // we run the countdown animation
        this.runner.run(
                new CountdownAnimation(3, 3, this.sprites, background));
        this.shouldStop = false;
        // use our runner to run the current animation -- which is one
        // turn of
        // the game.
        this.runner.run(this);
        return;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //check whether to display the pause screen
        if (this.gui.getKeyboardSensor().isPressed("p") || this.gui.getKeyboardSensor().isPressed("P")) {
            //we add an animation stopper
            this.runner.run(new KeyPressStoppableAnimation(
                    this.gui.getKeyboardSensor(), "space",
                    new PauseScreen()));
        }
        //we draw the game's background
        this.background = info.getBackground();
        this.background.drawOn(d);

        //printing the hearts
        Heart hearts = new Heart(ballsCounter.getValue());
        hearts.drawOn(d);

        //we draw the game's objects
        this.sprites.drawAllOn(d);
        //we "move" the game one step
        this.sprites.notifyAllTimePassed();
        //if the user won
        if (blocksCounter.getValue() <= M0) {
            if (blocksCounter.getValue() == M0) {
                //we go on another one loop
                blocksCounter.decrease(M1);
                return;
            }
            if (!win) {
                //we increase the 100 points for the level
                scoreTrackingListener.getCurrentScore().increase(100);
                    /*we indicate so, and going on another loop to print
                    the WIN notification*/
                this.win = true;
                return;
            }

            if (win) {
                this.shouldStop = true;
                Sleeper sleeper = new Sleeper();
                //we display the win
                //we close the game
                sleeper.sleepFor(FINISH_TIME);
                return;
            }
        }
        if (ballsCounter.getValue() == M0) {
            if (!lost) {

                    /*we indicate so, and going on another loop to print
                    the WIN notification*/
                this.lost = true;
                return;
            }

            if (lost) {
                this.shouldStop = true;
                Sleeper sleeper = new Sleeper();
                //we display the win
                sleeper.sleepFor(FINISH_TIME);
                //we close the game
                return;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Gets blocks.
     *
     * @return the blocks
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * Blocks left int.
     *
     * @return the int
     */
    public int blocksLeft() {
        return this.blocksCounter.getValue();
    }

    /**
     * Balls left int.
     *
     * @return the int
     */
    public int ballsLeft() {
        return this.ballsCounter.getValue();
    }

    /**
     * Stop.
     */
    public void stop() {
    }
}