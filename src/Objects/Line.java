package Objects;
import Interfaces.*;// 325764215 Noam Leabovich

import java.util.ArrayList;
import java.util.List;

/**
 * The type Graphics.Line.
 */
public class Line {
    /**
     * The constant M2.
     */
    public static final int M0 = 0;
    /**
     * The constant M4.
     */
    public static final int M4 = 4;
    /**
     * The constant M1.
     */
    public static final int M1 = 1;
    /**
     * The constant M2.
     */
    public static final int M2 = 2;
    /**
     * The constant M3.
     */
    public static final int M3 = 3;
    private final Point start;
    private final Point end;

    /**
     * Instantiates a new Graphics.Line.
     * first we check if there is a slope. if it doesn't, the lower
     * point will be the start. if it does, the point with the smaller x
     * value will be the start. this way it would be easier to calculate
     * things later.
     *
     * @param start the start point
     * @param end   the end point
     */
// constructors
    public Line(Point start, Point end) {
        MathCalculation cal = new MathCalculation();
        if (cal.valuesEqByEps(start.getX(), end.getX())) {
            if (start.getY() < end.getY()) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        } else if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    /**
     * Instantiates a new Graphics.Line.
     * first we check if there is a slope. if it doesn't, the lower
     * point will be the start. if it does, the point with the smaller x
     * value will be the start. this way it would be easier to calculate
     * things later.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        MathCalculation cal = new MathCalculation();

        if (cal.valuesEqByEps(x1, x2)) {
            if (y1 < y2) {
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                this.start = start;
                this.end = end;
            } else {
                Point end = new Point(x1, y1);
                Point start = new Point(x2, y2);
                this.start = start;
                this.end = end;
            }
        } else if (x1 < x2) {
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            this.start = start;
            this.end = end;
        } else {
            Point end = new Point(x1, y1);
            Point start = new Point(x2, y2);
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Length double.
     * simple calculation using a point method
     *
     * @return the length
     */
    public double length() {
        return this.start.distance(this.end); //using the point's method
    }

    /**
     * Middle point.
     * simple calculation of average
     *
     * @return the middle point
     */
    public Point middle() {
        double midX = ((this.start.getX() + (this.end.getX())) / M2);
        double midY = ((this.start.getY() + (this.end.getY())) / M2);
        return new Point(midX, midY); //simple calculation
    }

    /**
     * Start point.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }


    /**
     * Graphics.Point in line boolean.
     * we check if a certain value is in the range of a line
     *
     * @param x the point that we check for
     * @return the boolean if it's it the range
     */
    public Boolean pointInLine(double x) {
        MathCalculation cal = new MathCalculation();
        return (cal.biggerOrEqByEps(x, this.start.getX()))
                && (cal.smallerOrEqByEps(x, this.end.getX()));
    }

    /**
     * Graphics.Point in line boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public Boolean pointInLine(Point point) {
        MathCalculation cal = new MathCalculation();
        if ((cal.biggerOrEqByEps(point.getX(), this.start.getX()))
                && (cal.smallerOrEqByEps(point.getX(), this.end.getX()))) {
            return (cal.biggerOrEqByEps(point.getY(), this.start.getY()))
                    &&
                    (cal.smallerOrEqByEps(point.getY(),
                            this.end.getY()));
        } else {
            return false;
        }
    }


    /**
     * Is intersecting boolean.
     * we'll divide it into cases of slopes.
     * -only one line has slope, both, or none.
     * using some math calculation we'll check if there is an intersection
     *
     * @param other the other line
     * @return the boolean if there is an intersection
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        MathCalculation cal = new MathCalculation();

        //same line
        if (cal.pointsEqByEps(this.end, other.end)
                && cal.pointsEqByEps(this.start,
                other.start)) {
            return true;
        }

        // one continuing the other
        if (cal.pointsEqByEps(other.end, this.start)) {
            // one continuing the other
            return true;
        }

        // one continuing the other
        if (cal.pointsEqByEps(this.end, other.start)) {
            return true;
        }

        // all the cases when both have m = 0
        if (cal.valuesEqByEps(this.start.getX(),
                this.end.getX())
                && cal.valuesEqByEps(other.start.getX(),
                other.end.getX())) {

            /*if their x isn't the same there is no chance for
            /intersection*/

            if (!cal.valuesEqByEps(this.end.getX(),
                    other.end.getX())) {
                return false;
                // if the other line goes over this line (distance
                // comparing)
            } else if (cal.smallerOrEqByEps(
                    this.start.distance(other.start),
                    this.start.distance(this.end))) {
                return true;
                // if this line goes over the other line (distance
                // comparing)
            } else {
                return cal.smallerOrEqByEps(
                        other.start.distance(this.start),
                        other.start.distance(other.end));
            }
            //we returned false if there was no match
        }

        //if only this line has m = 0
        if (cal.valuesEqByEps(this.start.getX(),
                this.end.getX())) {
            //we check that the other line is in the range of this line
            if ((cal.biggerOrEqByEps(this.start.getX(),
                    other.start.getX()))
                    && (cal.smallerOrEqByEps(this.start.getX(),
                    other.end.getX()))) {
                /*we find the Func of the other line and look for an
                intersection with this line */
                double m2 =
                        ((other.end.getY() - other.start.getY())
                                / (other.end.getX() - other.start.getX()));
                double b2 = other.start.getY() - m2 * other.start.getX();
                double checkY = m2 * this.start.getX() + b2;
                /*we check if the intersection is in the range of this
                line in terms of height */
                return (cal.biggerOrEqByEps(checkY,
                        this.start.getY()))
                        && (cal.smallerOrEqByEps(checkY,
                        this.end.getY()));
            } else {
                //on every other case we return false
                return false;
            }
        }
        //if only the other line has m = 0
        if (cal.valuesEqByEps(other.start.getX(),
                other.end.getX())) {
            //we check that this line is in the range of the other line
            if ((cal.biggerOrEqByEps(other.start.getX(),
                    this.start.getX()))
                    && (cal.smallerOrEqByEps(other.start.getX(),
                    this.end.getX()))) {
                /*we find the Func of this line and look for an
                intersection with the other line */
                double m1 =
                        ((this.end.getY() - this.start.getY())
                                / (this.end.getX() - this.start.getX()));
                double b1 = this.start.getY() - m1 * this.start.getX();
                double checkY = m1 * other.start.getX() + b1;
                /*we check if the intersection is in the range of the
                other line in terms of height */
                return (cal.biggerOrEqByEps(checkY,
                        other.start.getY())
                        && (cal.smallerOrEqByEps(checkY,
                        other.end.getY())));
            } else {
                //on every other case we return false
                return false;
            }
        }
        /*from now on it means that for both m != 0
        we find the Func of both lines y = m*x + b*/
        double m1 =
                ((this.end.getY() - this.start.getY())
                        / (this.end.getX() - this.start.getX()));
        double m2 =
                ((other.end.getY() - other.start.getY())
                        / (other.end.getX() - other.start.getX()));

        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        //if they have the same 'm' but different 'b', no chance of "inter"
        if ((cal.valuesEqByEps(m1, m2))
                && (!cal.valuesEqByEps(b1, b2))) {
            return false;
        }
        //we check for "inter" when both 'm' and 'b' are the same
        if ((cal.valuesEqByEps(m1, m2))
                && (cal.valuesEqByEps(b1, b2))) {
            //if the other line goes ove this line
            if (cal.smallerOrEqByEps(
                    this.start.distance(other.start),
                    this.start.distance(this.end))) {
                return true;
            }
            //if this line goes over the other line
            if (cal.smallerOrEqByEps(
                    other.start.distance(this.start),
                    other.start.distance(other.end))) {
                return true;
            }
        }

        /*if they have different m we find the "suspicious" point, and
        we make sure that it's int the range of both lines. that's
        because these are not actually Func but more like vectors*/

        double interX = ((b2 - b1) / (m1 - m2));
        return this.pointInLine(interX) && other.pointInLine(interX);
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        MathCalculation cal = new MathCalculation();
        //return null if there is no "inter"
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if the lines are actually two dots that are equals
        if (cal.valuesEqByEps(this.start.getX(),
                this.end.getX())
                && cal.valuesEqByEps(other.start.getX(),
                other.end.getX())) {
            if (cal.valuesEqByEps(this.end.getY(),
                    other.start.getY())) {
                //we simply return one dot in that case
                return this.end;
            } else if (cal.valuesEqByEps(other.end.getY(),
                    this.start.getY())) {
                //same this I just make sure
                return this.start;
            } else {
                //if the dots aren't the same there is not "inter"
                return null;
            }
        }
        //this line has an m = 0
        if (cal.valuesEqByEps(this.start.getX(),
                this.end.getX())) {
            //we check that the other line is in the range of this line
            if (cal.biggerOrEqByEps(this.start.getX(),
                    other.start.getX())
                    && (cal.smallerOrEqByEps(this.start.getX(),
                    other.end.getX()))) {
                /*we find the Func of the other line and look for an
                intersection with this line */
                double m2 =
                        ((other.end.getY() - other.start.getY())
                                / (other.end.getX() - other.start.getX()));
                double b2 = other.start.getY() - m2 * other.start.getX();
                double checkY = m2 * this.start.getX() + b2;
                /*we check if the intersection is in the range of this
                line in terms of height. if it does we return it */
                if (cal.biggerOrEqByEps(checkY,
                        this.start.getY())
                        && (cal.smallerOrEqByEps(checkY,
                        this.end.getY()))) {
                    return new Point(this.start.getX(), checkY);
                }
                //on every other case we return null
            } else {
                return null;
            }
        }

        //if only the other line has m = 0
        if (cal.valuesEqByEps(other.start.getX(),
                other.end.getX())) {
            //we check that this line is in the range of the other line
            if ((cal.biggerOrEqByEps(other.start.getX(),
                    this.start.getX()))
                    && (cal.smallerOrEqByEps(other.start.getX(),
                    this.end.getX()))) {
                /*we find the Func of this line and look for an
                intersection with the other line */
                double m1 =
                        ((this.end.getY() - this.start.getY())
                                / (this.end.getX() - this.start.getX()));
                double b1 = this.start.getY() - m1 * this.start.getX();
                double checkY = m1 * other.start.getX() + b1;
                /*we check if the intersection is in the range of this
                line in terms of height. if it does we return it */
                if ((cal.biggerOrEqByEps(checkY,
                        other.start.getY()))
                        && (cal.smallerOrEqByEps(checkY,
                        other.end.getY()))) {
                    return new Point(other.start.getX(), checkY);
                }
                //on every other case we return null
            } else {
                return null;
            }
        }

        /*from now on it means that for both m != 0
        we find the Func of both lines y = m*x + b*/
        double m1 =
                ((this.end.getY() - this.start.getY())
                        / (this.end.getX() - this.start.getX()));
        double m2 =
                ((other.end.getY() - other.start.getY())
                        / (other.end.getX() - other.start.getX()));
        /*if they have the same 'm' but one "over-goes" on the other we
        return null*/
        if (cal.valuesEqByEps(m1, m2)) {
            if (this.equals(other) && !cal.pointsEqByEps(this.end,
                    this.start)) {
                return null;
            }
            //if they both the same dot with a slope somehow..
            if (this.equals(other) && cal.pointsEqByEps(this.end,
                    this.start)) {
                return this.start;
            }
            //one continuing the other
            if (cal.pointsEqByEps(this.start, other.end)) {
                return this.start;
                //one continuing the other
            } else if (cal.pointsEqByEps(this.end, other.start)) {
                return this.end;
            } else {
                /* on every other case it means there is more than one
                point of intersection, so we return null*/
                return null;
            }
        }
        /*if the method reaches here it means we have a simple
        intersection case, so we calculate the point and return it*/
        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        double interX = ((b2 - b1) / (m1 - m2));
        double interY = m1 * interX + b1;
        return new Point(interX, interY);
    }

    /**
     * Equals boolean.
     * return true is the lines are equal, false otherwise
     *
     * @param other the other line
     * @return the boolean whether it's the same lime
     */
    public boolean equals(Line other) {
        MathCalculation cal = new MathCalculation();
        return cal.pointsEqByEps(other.start,
                this.start) && cal.pointsEqByEps(other.end,
                this.end);
    }

    /**
     * Closest intersection to start of line point.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle
     * @return the closest Intersection point To Start Of the Graphics.Line
     */
//
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //we set an indicator
        int indicator = M0;
        //we simplify to get the rectangle's 4 corner points
        Point upperRight =
                new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY());
        Point downerLeft = new Point(rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());
        Point downerRight = new Point(rect.getUpperLeft().getX()
                + rect.getWidth(),
                upperRight.getY() + rect.getHeight());
        //we save the rectangles' lines in an array
        Line[] lines = new Line[M4];
        //we create a list for the potential points
        List<Point> list = new ArrayList<>();
        //inserting the lines
        lines[M0] = new Line(upperRight, rect.getUpperLeft());
        lines[M1] = new Line(upperRight, downerRight);
        lines[M2] = new Line(downerRight, downerLeft);
        lines[M3] = new Line(rect.getUpperLeft(), downerLeft);

        /*going over the lines looking for intersection with the
        rectangle's borderlines*/

        for (int i = M0; i < M4; i++) {
            if (this.isIntersecting(lines[i])) {
                if (this.intersectionWith(lines[i]) != null) {
                    list.add(this.intersectionWith(lines[i]));
                    ++indicator;
                }
            }
        }
        //if there was no intersection found we return so
        if (indicator == M0) {
            return null;
        }
        //we set an initial distance and index
        double distance;
        int index;
        distance = this.start.distance(list.get(M0));
        index = M0;
        for (int i = M1; i < list.size(); i++) {
            /*if a smaller distance is found we update the distance
            and the index*/
            if (this.start.distance(list.get(i)) < distance) {
                distance = this.start.distance(list.get(i));
                index = i;
            }
        }
        //we return the closest Intersection point To Start Of the Graphics.Line
        return list.get(index);
    }

    /**
     * Graphics.Line of intersection line.
     * we do the same process of the point intersection, but this time
     * we return the line of the intersection
     *
     * @param rect the rectangle
     * @return the line where the intersection is
     */
    public Line lineOfIntersection(Rectangle rect) {
        //we set an indicator
        int indicator = M0;
        //we simplify to get the rectangle's 4 corner points
        Point upperRight =
                new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                        rect.getUpperLeft().getY());
        Point downerLeft = new Point(rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());
        Point downerRight = new Point(rect.getUpperLeft().getX()
                + rect.getWidth(),
                upperRight.getY() + rect.getHeight());
        //we save the rectangles' lines in an array
        Line[] lines = new Line[M4];
        //we create a list for the potential points
        List<Point> list = new ArrayList<>();
        //inserting the lines
        lines[M0] = new Line(upperRight, rect.getUpperLeft());
        lines[M1] = new Line(upperRight, downerRight);
        lines[Line.M2] = new Line(downerRight, downerLeft);
        lines[M3] = new Line(rect.getUpperLeft(), downerLeft);

        /*going over the lines looking for intersection with the
        rectangle's borderlines*/

        for (int i = M0; i < M4; i++) {
            if (this.isIntersecting(lines[i])) {
                if (this.intersectionWith(lines[i]) != null) {
                    list.add(this.intersectionWith(lines[i]));
                    ++indicator;
                }
            }
        }
        //if there was no intersection found we return so
        if (indicator == M0) {
            return null;
        }
        //we set an initial distance and index
        double distance;
        int index;
        distance = this.start.distance(list.get(M0));
        index = M0;
        for (int i = M1; i < list.size(); i++) {
            /*if a smaller distance is found we update the distance
            and the index*/
            if (this.start.distance(list.get(i)) < distance) {
                distance = this.start.distance(list.get(i));
                index = i;
            }
        }
        //we return the line with the Intersection point
        if (lines[M0].pointInLine(list.get(index))) {
            return lines[M0];
        }
        if (lines[M1].pointInLine(list.get(index))) {
            return lines[M1];
        }
        if (lines[Line.M2].pointInLine(list.get(index))) {
            return lines[Line.M2];
        }
        return lines[M3];
    }
}
