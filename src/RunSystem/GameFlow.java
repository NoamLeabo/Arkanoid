package RunSystem;// 325764215 Noam Leabovich
import java.util.List;
import Interfaces.*;
import GameLogic.*;
import Objects.*;
import biuoop.GUI;


/**
 * The type Game flow.
 */
public class GameFlow {
    public static final int DEF = 0;
    public static final int M1 = 1;
    private AnimationRunner ar;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the AnimationRunner
     */
    public GameFlow(AnimationRunner ar) {
        this.ar = ar;
        this.gui = ar.getGui();
    }

    /**
     * Run levels.
     * we receive a list of levels and run them until the user loses, or
     * until the list of levels is over
     *
     * @param levels the game levels
     */
    public void runLevels(List<LevelInformation> levels) {
        StartScreen startScreen = new StartScreen();

        //we create a score indicator that runs along each of the levels
        ScoreTrackingListener scoreTrackingListener =
                new ScoreTrackingListener(new Counter(DEF));
        this.ar.run(new KeyPressStoppableAnimation(
                this.gui.getKeyboardSensor(), "space",
                new StartScreen()));
        //we run each level
        for (LevelInformation levelInfo : levels) {
            //we set the level with the given level info
            GameLevel level = new GameLevel(levelInfo, this.gui, this.ar,
                    scoreTrackingListener);
            //we initialize the level
            level.initialize();
            /*we run the level until either the blocks or the balls are
            over*/
            while (level.blocksLeft() != -M1 && level.ballsLeft() != DEF) {
                level.run();
            }
            //if the balls are all gone, the user lost and the game stops
            if (level.ballsLeft() == DEF) {
                //we display the end screen with an "animation stopper"
                this.ar.run(new KeyPressStoppableAnimation(
                        this.gui.getKeyboardSensor(), "space",
                        new EndScreen(this.gui.getKeyboardSensor(),
                                scoreTrackingListener, false)));
                //we close the gui and end the game
                this.gui.close();
                break;
            }
        }

        /*if the user won we display the win end screen with an
        animation stopper*/

        this.ar.run(new KeyPressStoppableAnimation(
                this.gui.getKeyboardSensor(), "space",
                new EndScreen(this.gui.getKeyboardSensor(),
                        scoreTrackingListener, true)));
        //we close the gui
        this.gui.close();
    }
}