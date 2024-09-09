// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;
/**
 * The type Graphics.Point.
 */
public class Point {

    private final double x;
    private final double y;

    /**
     * Instantiates a new Graphics.Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = Math.round(x * 1000.0) / 1000.0;
        this.y = Math.round(y * 1000.0) / 1000.0;
    }

    /**
     * Distance double.
     * we calculate the distance using a simple formula
     *
     * @param other the other
     * @return the distance between the two points
     */

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double needRoot =
                ((this.x - other.x) * (this.x - other.x))
                        + ((this.y - other.y) * (this.y - other.y));
        return Math.sqrt(needRoot);
    }

    /**
     * Equals boolean.
     * check if two lines have the same start&end point, and so they are
     * the same
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        MathCalculation cal = new MathCalculation();

        return cal.valuesEqByEps(this.getX(),
                other.getX()) && cal.valuesEqByEps(this.getY(),
                other.getY());
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
