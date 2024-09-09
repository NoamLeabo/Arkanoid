package RunSystem;// 325764215 Noam Leabovich
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Interfaces.*;
import GameLogic.*;
import Objects.*;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    public static final int DEF = 0;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int M1000 = 1000;
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui             the gui
     * @param sleeper         the sleeper
     * @param framesPerSecond the frames per second
     */
// ...
    public AnimationRunner(GUI gui, Sleeper sleeper, int framesPerSecond) {
        this.sleeper = sleeper;
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int framesPerSecond = FRAMES_PER_SECOND;
        int millisecondsPerFrame = M1000 / framesPerSecond;
        //run the animation as long as it's needed
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            //we get the drawSurface
            DrawSurface d = gui.getDrawSurface();
            //we do one frame
            animation.doOneFrame(d);
            //we show the board
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > DEF) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
                //this.sleeper.sleepFor(250);
            }
        }
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }
}
