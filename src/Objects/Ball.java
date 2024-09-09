// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The type Graphics.Graphics.Ball.Ball.
 */
public class Ball implements Sprite, HitNotifier {
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    /**
     * The constant COLOR.
     */
    public static final int COLOR = 256;
    private final int radius;
    protected Point center;
    private java.awt.Color color;
    protected Velocity velocity = new Velocity(M0, M0);
    //"Has been pushed" into the surface.
    private boolean hasBeenPushedIn = false;
    protected GameEnvironment gameEnvironment;
    private int maxY = 500;
    private int maxX = 500;
    private int minY = M0;
    private int minX = M0;
    private int speed = 3;
    private boolean inGame = false;

    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Graphics.Graphics.Ball.Ball.
     *
     * @param center the center of the ball
     * @param r      the radius
     * @param color  the color of the ball
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Graphics.Graphics.Ball.Ball.
     *
     * @param centerX the ball's center value of x
     * @param centerY the ball's center value of y
     * @param r       the radius
     * @param color   the color of the ball
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color) {
        this.center = new Point(centerX, centerY);
        this.radius = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    public void gameStatus(){
        this.inGame = !inGame;
    }

    /**
     * Set new max.
     * ball's max area values
     *
     * @param newMaxX the new max x
     * @param newMaxY the new max y
     */
    public void setNewMax(int newMaxX, int newMaxY) {
        this.maxY = newMaxY;
        this.maxX = newMaxX;
    }

    public int getRadius() {
        return radius;
    }

    /**
     * Set new min.
     * ball's min area values
     *
     * @param newMinX the new min x
     * @param newMinY the new min y
     */
    public void setNewMin(int newMinX, int newMinY) {
        this.minY = newMinY;
        this.minX = newMinX;
    }

    public boolean isInGame() {
        return inGame;
    }

    /**
     * Get x int.
     *
     * @return the x of the ball's center
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get y int.
     *
     * @return the y of the ball's center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get ball's size.
     *
     * @return the ball's radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get color of the ball.
     *
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    public Point getCenter() {
        return center;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set x.
     *
     * @param x the new x value
     */
    public void setX(double x) {
        this.center = new Point(x, this.center.getY());
    }

    /**
     * Set y.
     *
     * @param y the new y value
     */
    public void setY(double y) {
        this.center = new Point(this.center.getX(), y);
    }

    /**
     * Draw on.
     * draws the ball on the given DrawSurface
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Set velocity.
     * set velocity by its components
     *
     * @param dx the x value ov the velocity
     * @param dy the y value ov the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Set velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * In zone boolean.
     * check if the ball in the demanded area
     *
     * @param maxX the max x value
     * @param maxY the max y value
     * @param minX the min x value
     * @param minY the min y value
     * @return the boolean result
     */
    public Boolean inZone(int maxX, int maxY, int minX, int minY) {
        if (this.center.getX() - this.radius < minX) {
            return false;
        } else if (this.center.getX() + this.radius > maxX) {
            return false;
        } else if (this.center.getY() - this.radius < minY) {
            return false;
        } else {
            return !(this.center.getY() + this.radius > maxY);
        }
    }

    /**
     * Feel rainbow. changes the ball's color
     */
    public void iFeelRainbow() {
        Random rand = new Random();
        this.color = new Color(rand.nextInt(COLOR), rand.nextInt(COLOR),
                rand.nextInt(COLOR));
    }

    /**
     * Fix ball placement.
     * if the ball ain't completely inside the surface it fixes its
     * placement by putting it inside the closest to its original respawn
     *
     * @param maxX the max x
     * @param maxY the max y
     * @param minX the min x
     * @param minY the min y
     */
    public void fixBallPlacement(int maxX, int maxY, int minX, int minY) {
        if (this.center.getX() - this.radius < minX) {
            this.center = new Point(radius, this.center.getY());
        }
        if (this.center.getX() + this.radius > maxX) {
            this.center = new Point(maxX - radius, this.center.getY());
        }
        if (this.center.getY() - this.radius < minY) {
            this.center = new Point(this.center.getX(), radius);
        }
        if (this.center.getY() + this.radius > maxY) {
            this.center = new Point(this.center.getX(), maxY - radius);
        }
    }


    /**
     * Moves the ball one step.
     * we have a few exceptions here. in case that the ball is just
     * about to hit the corner perfectly we will change the
     * direction of the velocity as needed, so it's going exactly the
     * opposite way. we check for both the x value and the y value
     * separately
     * now, in case that a ball will pass the border on the next
     * step, the ball will do a shorter step of the exact required
     * distance. every single time we indicate in which
     * parameter we moved
     * (x or y), so will do "MoveOneStep" only on the other
     * parameter.
     * of course that if we attached the ball on both
     * parameters, we
     * will not be doing any "MoveOneStep" to none of the
     * Parameter
     */
    public void moveOneStep1() {
        MathCalculation cal = new MathCalculation();
        /*first we make sure that the ball is inside the screen. if
        needed it pushes it inside*/
        if (!hasBeenPushedIn) {
            if (!this.inZone(maxX, maxY, minX, minY)) {
                fixBallPlacement(maxX, maxY, minX, minY);
            }
            hasBeenPushedIn = true;
        }

        //the ball hits the bottom right corner
        if (this.center.getX() + this.radius == maxX
                && this.center.getY() + this.radius == maxY) {
            //flips the x value of the velocity if needed
            if (this.velocity.getDx() > M0) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        this.velocity.getDy());
            }
            //flips the y value of the velocity if needed
            if (this.velocity.getDy() > M0) {
                this.velocity = new Velocity(this.velocity.getDx(),
                        -this.velocity.getDy());
            }
            //changes the color for fun
            iFeelRainbow();
            //apply the velocity
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //the ball hits the bottom left corner
        if (this.center.getX() - this.radius == minX
                && this.center.getY() + this.radius == maxY) {
            //flips the y value of the velocity if needed
            if (this.velocity.getDy() > M0) {
                this.velocity = new Velocity(this.velocity.getDx(),
                        -this.velocity.getDy());
            }
            //flips the x value of the velocity if needed
            if (this.velocity.getDx() < M0) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        this.velocity.getDy());
            }
            //changes the color for fun
            iFeelRainbow();
            //apply the velocity
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //the ball hits the top left corner
        if (this.center.getY() - this.radius == minY
                && this.center.getX() - this.radius == minX) {
            //flips the x value of the velocity if needed
            if (this.velocity.getDx() < M0) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        this.velocity.getDy());
            }
            //flips the y value of the velocity if needed
            if (this.velocity.getDy() < M0) {
                this.velocity = new Velocity(this.velocity.getDx(),
                        -this.velocity.getDy());
            }
            //apply the velocity
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //the ball hits the top right corner
        if (this.center.getY() - this.radius == minY
                && this.center.getX() + this.radius == maxX) {
            //flips the x value of the velocity if needed
            if (this.velocity.getDx() > M0) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        this.velocity.getDy());
            }
            //flips the y value of the velocity if needed
            if (this.velocity.getDy() < M0) {
                this.velocity = new Velocity(this.velocity.getDx(),
                        -this.velocity.getDy());
            }
            //apply the velocity
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        /*
        x1-right border of x
        x2-left border of x
        y1-bottom border of y
        y2-top border of y
        */

        boolean x1 = false;
        boolean y1 = false;
        boolean x2 = false;
        boolean y2 = false;

        //attach to the right x border
        if (cal.biggerOrEqByEps(
                this.center.getX() + this.radius + this.velocity.getDx(),
                maxX)) {
            this.center = new Point(maxX - radius, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDx(),
                    this.velocity.getDy());
            iFeelRainbow();
            x1 = true;
        }
        //attach to the bottom y border
        if (cal.biggerOrEqByEps(
                this.center.getY() + this.radius + this.velocity.getDy(),
                maxY)) {
            this.center = new Point(this.center.getX(), maxY - radius);
            this.velocity = new Velocity(this.velocity.getDx(),
                    -this.velocity.getDy());
            y1 = true;
        }
        //attach to the left x border
        if (cal.smallerOrEqByEps(
                this.center.getX() - this.radius + this.velocity.getDx(),
                minX)) {
            this.center = new Point(minX + radius, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDx(),
                    this.velocity.getDy());
            iFeelRainbow();
            x2 = true;
        }
        //attach to the top y border
        if (cal.smallerOrEqByEps(
                this.center.getY() - this.radius + this.velocity.getDy(),
                minY)) {
            this.center = new Point(this.center.getX(), minY + radius);
            this.velocity = new Velocity(this.velocity.getDx(),
                    -this.velocity.getDy());
            y2 = true;
        }
        //completing the steps needed
        if ((y1 && !x1 && !x2) || (y2 && !x1 && !x2)) {
            this.center =
                    this.getVelocity().applyToPointOnlyX(this.center);
            return;
        }
        if ((x1 && !y1 && !y2) || (x2 && !y1 && !y2)) {
            this.center =
                    this.getVelocity().applyToPointOnlyY(this.center);
            return;
        }
        if (!x1 && !x2 && !y1 && !y2) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Add to game.
     * add this ball to the game
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Move the ball one step.
     */
    public void moveOneStep() {

        Point end = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, end);
        CollisionInfo collisionInfo =
                this.gameEnvironment.getClosestCollision111(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.velocity =
                collisionInfo.collisionObject()
                        .hit(this, collisionInfo.collisionPoint(),
                                this.velocity);

        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Remove from game.
     * remove this ball from the game
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * Add hit listener.
     * add a new listener to this ball
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     * remove a listener from this ball
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}