// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
/**
 * The type Graphics.Rectangle.
 */
public class Rectangle {
    public static final int M4 = 4;
    public static final int M0 = 0;
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Instantiates a new Graphics.Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point
     * @param width     the width of the Graphics.Rectangle
     * @param height    the height of the Graphics.Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
    }

    /**
     * Intersection points java . util . list.
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line with the suspected intersections
     * @return the final java . util . list
     */

    public boolean lineInRectangle(Line line) {
        Point upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        Point downerLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        Point downerRight = new Point(upperLeft.getX() + width,
                upperRight.getY() + height);
        //we set a new array of hte rectangle borderlines
        Line[] lines = new Line[M4];
        lines[M0] = new Line(upperRight, upperLeft);
        lines[1] = new Line(upperRight, downerRight);
        lines[2] = new Line(downerRight, downerLeft);
        lines[3] = new Line(upperLeft, downerLeft);
        int indicator = M0;
        for (int i = M0; i < M4; i++) {
            if (lines[i].equals(line)) {
                ++indicator;
            }
        }
        return indicator != M0;
    }

    /**
     * Get width double.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     *
     * @return the width of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     * Returns the upper-left point of the rectangle.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}