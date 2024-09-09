// 325764215 Noam Leabovich


import GameLogic.*;
import RunSystem.*;
import Interfaces.*;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Start.
 */
public class Ass6Game {

    public static final String LEVEL1 = "1";
    public static final String LEVEL2 = "2";
    public static final String LEVEL3 = "3";
    public static final String LEVEL4 = "4";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int DEF = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //we create a gui
        GUI gui = new GUI("Game", WIDTH, HEIGHT);
        //we create a runner for the gui
        AnimationRunner animationRunner = new AnimationRunner(gui,
                new Sleeper(), FRAMES_PER_SECOND);
        //we create a gameFlow
        GameFlow gameFlow = new GameFlow(animationRunner);
        //we create a copy of each of the levels
        GameLevel1 gameLevel1 = new GameLevel1();
        GameLevel2 gameLevel2 = new GameLevel2();
        GameLevel3 gameLevel3 = new GameLevel3();
        GameLevel4 gameLevel4 = new GameLevel4();
        //we create a list of the levels that we will be running
        List<LevelInformation> informations = new ArrayList<>();
        //we add the required levels by from the command line
        for (int i = DEF; i < args.length; i++) {
            if (args[i].matches(LEVEL1)) {
                informations.add(new GameLevel1());
            } else if (args[i].matches(LEVEL2)) {
                informations.add(new GameLevel2());
            } else if (args[i].matches(LEVEL3)) {
                informations.add(new GameLevel3());
            } else if (args[i].matches(LEVEL4)) {
                informations.add(new GameLevel4());
            }

        }
        //if there are levels received we run them
        if (informations.size() != DEF) {
            gameFlow.runLevels(informations);
        } else {
            //if there are no levels received we simply run the basic 3
            informations.add(gameLevel3);
            informations.add(gameLevel4);
            gameFlow.runLevels(informations);
        }
    }
}
