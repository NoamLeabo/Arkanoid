// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
/**
 * The type Collision info.
 */
public class CollisionInfo {
    private final Point point;
    private final Collidable collidable;

    /**
     * Instantiates a new Collision info.
     *
     * @param point      the point of the collision
     * @param collidable the collidable item at which the
     *                   collision occurred
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    /**
     * Collision point of the collision.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}