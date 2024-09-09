// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import RunSystem.*;
/**
 * The type Graphics.Ball.Velocity.
 */
public class Velocity {

    private final double dx;
    private final double dy;

    /**
     * Instantiates a new Graphics.Ball.Velocity.
     *
     * @param dx the x velocity's value
     * @param dy the y velocity's value
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     * we creat velocity, this time the args are the speed and the angle
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //simple calculation from 8th grade
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * getDx.
     * Get vel x value of the velocity
     *
     * @return the x value of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy.
     * Get vel y value of the velocity
     *
     * @return the y value of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Apply to point.
     * apple a certain velocity to a point
     * the Func takes a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p the point
     * @return the point with its updated location
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Apply to point only x point.
     * apple a certain velocity to a point
     * the Func takes a point with position (x,y) and return a new point
     * with position (x+dx, y)
     *
     * @param p the point
     * @return the point with its updated location
     */
    public Point applyToPointOnlyX(Point p) {
        return new Point(p.getX() + dx, p.getY());
    }

    /**
     * Apply to point only y point.
     * apple a certain velocity to a point
     * the Func takes a point with position (x,y) and return a new point
     * with position (x, y+dy)
     *
     * @param p the point
     * @return the point with its updated location
     */
    public Point applyToPointOnlyY(Point p) {
        return new Point(p.getX(), p.getY() + dy);
    }

}
