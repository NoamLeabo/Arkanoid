// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
/**
 * The type Math calculation.
 */
public class MathCalculation {
    /**
     * The constant Epsilon.
     */
    private final double ePsilon = 0.00001;

    /**
     * Instantiates a new Math calculation.
     */
    public MathCalculation() {
    }

    /**
     * Values equal by epsilon boolean.
     * we check if a subtract of two nums in absolute value is less than
     * epsilon
     *
     * @param a the first num
     * @param b the second num
     * @return the boolean if they are equal by epsilon
     */
    public boolean valuesEqByEps(double a, double b) {
        return Math.abs(a - b) < ePsilon;
    }

    /**
     * Values equal by epsilon boolean for points.
     * we check if the distance of two points in absolute value is less
     * than epsilon
     *
     * @param a the first point
     * @param b the second point
     * @return the boolean if they are equal by epsilon
     */
    public boolean pointsEqByEps(Point a, Point b) {
        return valuesEqByEps(a.getY(),
                b.getY()) && valuesEqByEps(a.getX(), b.getX());
    }

    /**
     * Bigger or eq by eps boolean.
     * we check if one num is bigger than another or equals to it by
     * epsilon
     *
     * @param a the first num
     * @param b the second num
     * @return the boolean if one is bigger or equals
     */
    public boolean biggerOrEqByEps(double a, double b) {
        return (a > b) || valuesEqByEps(a, b);
    }

    /**
     * Smaller or eq by eps boolean.
     * we check if one num is smaller than another or equals to it by
     * epsilon
     *
     * @param a the first num
     * @param b the second num
     * @return the boolean if one is smaller or equals
     */
    public boolean smallerOrEqByEps(double a, double b) {
        return (a < b) || valuesEqByEps(a, b);
    }
}
