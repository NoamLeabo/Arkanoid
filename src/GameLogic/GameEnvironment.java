// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.Game environment.
 */
public class GameEnvironment {
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    /**
     * The constant M1.
     */
    public static final int M1 = 1;
    private final List<Collidable> collidables;

    /**
     * Instantiates a new Game.Game environment.
     * add the given collidable to the environment.
     *
     * @param collidables the game's collidables
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Add collidable.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removeollidable.
     *
     * @param c the c
     */
    public void removeollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets the closest collision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
//
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Integer> indexes = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        for (int i = M0; i < this.collidables.size(); i++) {
            Rectangle rectangle =
                    collidables.get(i).getCollisionRectangle();
            //if there is a collision we keep its point
            if (trajectory.closestIntersectionToStartOfLine(rectangle)
                    != null) {
                points.add(trajectory.closestIntersectionToStartOfLine(
                        rectangle));
                //the rectangle's line of the intersection
                lines.add(trajectory.lineOfIntersection(rectangle));
                //we also keep the collidable's index
                indexes.add(i);
            }
        }
        //if there was no collision we return so
        if (points.size() == M0) {
            return null;
        }
        /*we will sort the points so the points that are lines that on
        the block stack outline in the game will be checked first*/
        List<Point> pointsNew = new ArrayList<>();
        List<Integer> indexesNew = new ArrayList<>();
        //of course, it's only if we have more than one point
        if (points.size() != M1) {
            /*we will count in how many blocks the line of hte collision
            appears*/
            int k = M0;
            for (int i = M0; i < lines.size(); i++) {
                for (int j = M0; j < collidables.size(); j++) {
                    if (collidables.get(j).getCollisionRectangle()
                            .lineInRectangle(lines.get(i))) {
                        ++k;
                    }
                }
                if (k == M1) {
                    //we will save tha line and the block in the new list
                    pointsNew.add(points.get(i));
                    indexesNew.add(indexes.get(i));
                }
                //we start all over again
                k = M0;
            }
        }
        //if there was no line that appeared once the list is empty
        if (pointsNew.size() > M0) {
            //we set an initial distance and check if there is a smaller
            // one
            int index = M0;
            double distance =
                    trajectory.start().distance(pointsNew.get(M0));
            for (int i = M1; i < pointsNew.size(); i++) {
                //if there is a smaller one, we keep it and its index
                if (trajectory.start().distance(pointsNew.get(i))
                        < distance) {
                    distance =
                            trajectory.start().distance(pointsNew.get(i));
                    index = i;
                }
            }

        /*we return the collsion point and the collidable item of the
        collision*/
            return new CollisionInfo(pointsNew.get(index),
                    collidables.get(indexesNew.get(index)));
        }
        //we set an initial distance and check if there is a smaller one
        int index = M0;
        double distance =
                trajectory.start().distance(points.get(M0));
        for (int i = M1; i < points.size(); i++) {
            //if there is a smaller one, we keep it and its index
            if (trajectory.start().distance(points.get(i))
                    < distance) {
                distance =
                        trajectory.start().distance(points.get(i));
                index = i;
            }
        }

        /*we return the collsion point and the collidable item of the
        collision*/
        return new CollisionInfo(points.get(index),
                collidables.get(indexes.get(index)));
    }


    /**
     * Gets closest collision 111.
     *
     * @param trajectory the trajectory
     * @return the closest collision 111
     */
    public CollisionInfo getClosestCollision111(Line trajectory) {
        List<Integer> indexes = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        for (int i = M0; i < this.collidables.size(); i++) {
            Rectangle rectangle =
                    collidables.get(i).getCollisionRectangle();
            //if there is a collision we keep its point
            if (trajectory.closestIntersectionToStartOfLine(rectangle)
                    != null) {
                points.add(trajectory.closestIntersectionToStartOfLine(
                        rectangle));
                //the rectangle's line of the intersection
                lines.add(trajectory.lineOfIntersection(rectangle));
                //we also keep the collidable's index
                indexes.add(i);
            }
        }
        //if there was no collision we return so
        if (points.size() == M0) {
            return null;
        }
        stop();
        if (points.size() == M1) {
            return new CollisionInfo(points.get(M0),
                    collidables.get(indexes.get(M0)));
        }

        /*we will sort the points so the points that are lines that on
        the block stack outline in the game will be checked first*/
        //of course, it's only if we have more than one point
        //if there was no line that appeared once the list is empty
        //we set an initial distance and check if there is a smaller one
        int index = M0;
        double distance =
                trajectory.start().distance(points.get(M0));
        for (int i = M1; i < points.size(); i++) {
            //if there is a smaller one, we keep it and its index
            if (trajectory.start().distance(points.get(i))
                    < distance) {
                distance =
                        trajectory.start().distance(points.get(i));
                index = i;
            }
        }

        /*we return the collsion point and the collidable item of the
        collision*/
        CollisionInfo info1 = new CollisionInfo(points.get(index),
                collidables.get(indexes.get(index)));
        Line line1 = lines.get(index);

        List<Integer> indexes1 = new ArrayList<>();
        List<Point> points1 = new ArrayList<>();
        List<Line> lines1 = new ArrayList<>();

        for (int i = this.collidables.size() - 1; i >= 0; i--) {
            Rectangle rectangle =
                    collidables.get(i).getCollisionRectangle();
            //if there is a collision we keep its point
            if (trajectory.closestIntersectionToStartOfLine(rectangle)
                    != null) {
                points1.add(trajectory.closestIntersectionToStartOfLine(
                        rectangle));
                //the rectangle's line of the intersection
                lines1.add(trajectory.lineOfIntersection(rectangle));
                //we also keep the collidable's index
                indexes1.add(i);
            }
        }

        /*we will sort the points so the points that are lines that on
        the block stack outline in the game will be checked first*/
        //of course, it's only if we have more than one point
        //if there was no line that appeared once the list is empty
        //we set an initial distance and check if there is a smaller one
        int index1 = M0;
        double distance1 =
                trajectory.start().distance(points1.get(M0));
        for (int i = M1; i < points1.size(); i++) {
            //if there is a smaller one, we keep it and its index
            if (trajectory.start().distance(points1.get(i))
                    < distance1) {
                distance1 =
                        trajectory.start().distance(points1.get(i));
                index1 = i;
            }
        }
        CollisionInfo info = new CollisionInfo(points1.get(index1),
                collidables.get(indexes1.get(index1)));
        Line l = lines1.get(index1);

        if (info.collisionPoint().equals(info1.collisionPoint())) {
            return info;
        } else {
            if (l.start().getX() == l.end().getX()) {
                return info1;
            } else {
                return info;
            }
        }
    }

    /**
     * Stop.
     */
    public void stop() {
    }
}