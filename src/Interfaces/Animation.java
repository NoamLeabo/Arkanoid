package Interfaces;// 325764215 Noam Leabovich
import biuoop.DrawSurface;
import GameLogic.*;
import Objects.*;
import RunSystem.*;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean whether it shouldStop or not
     */
    boolean shouldStop();
}
