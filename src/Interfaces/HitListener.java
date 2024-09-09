package Interfaces;// 325764215 Noam Leabovich
import GameLogic.*;
import Objects.*;
import RunSystem.*;
/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Graphics.Graphics.Ball.Ball that's doing the hitting.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
