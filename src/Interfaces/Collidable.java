package Interfaces;// 325764215 Noam Leabovich
import GameLogic.*;
import Objects.*;
import RunSystem.*;
/**
 * The interface interfaces.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit updated velocity.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit
     * (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint,
                 Velocity currentVelocity);
}