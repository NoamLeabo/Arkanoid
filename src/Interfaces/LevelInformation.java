// 325764215 Noam Leabovich
package Interfaces;
import java.awt.Color;
import java.util.List;
import GameLogic.*;
import Objects.*;
import RunSystem.*;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the numberOfBalls
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * The initial velocity of each ball
     * @return the list of initialBallVelocities
     */

    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the paddleSpeed
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the paddleWidth
     */
    int paddleWidth();

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     * @return the string level Name
     */

    String levelName();

    /**
     * Gets background.
     * Returns a sprite with the background of the level
     * @return the background
     */

    Sprite getBackground();

    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return the int numberOfBlocksToRemove
     */
    int numberOfBlocksToRemove();

    /**
     * Gets balls.
     *
     * @return the balls
     */
    List<Ball> getBalls();

    /**
     * Paddle color.
     *
     * @return the paddleColor
     */
    Color paddleColor();

    /**
     * Gets paddle start.
     *
     * @return the paddle starting point
     */
    int getPaddleStart();
}