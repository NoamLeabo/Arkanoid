// 325764215 Noam Leabovich
package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * The type Graphics.Paddle.
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    /**
     * The constant M150.
     */
    public static final int M150 = 150;
    /**
     * The constant M250.
     */
    public static final int M250 = 250;
    /**
     * The constant M100.
     */
    public static final int M100 = 100;
    /**
     * The constant M565.
     */
    public static final int M565 = 565;
    /**
     * The constant M15.
     */
    public static final int M15 = 15;
    /**
     * The constant M7.
     */
    public static final int M7 = 7;
    /**
     * The constant M680.
     */
    public static final int M680 = 680;
    /**
     * The constant M20.
     */
    public static final int M20 = 20;
    /**
     * The constant M2.
     */
    public static final int M2 = 2;
    /**
     * The constant M60.
     */
    public static final int M60 = 60;
    /**
     * The constant M40.
     */
    public static final int M40 = 40;
    /**
     * The constant M30.
     */
    public static final int M30 = 30;
    /**
     * The constant M80.
     */
    public static final int M80 = 80;
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    /**
     * The constant M555.
     */
    public static final int M555 = 555;
    private final java.awt.Color color;
    private final biuoop.KeyboardSensor keyboard;
    private final List<Ball> balls;
    private Rectangle paddle;
    private Point upperLeft;
    private Point upperRight;
    private Point downerLeft;
    private Point downerRight;
    private int velocity;
    private int width;
    private IconRemover iconRemover;
    private BallAdder ballAdder;


    /**
     * Instantiates a new Graphics.Paddle.
     *
     * @param keyboard    the keyboard SENSOR
     * @param balls       the balls of the game
     * @param velocity    the velocity
     * @param width       the width
     * @param color       the color
     * @param start       the start
     * @param iconRemover
     * @param ballAdder
     */
    public Paddle(KeyboardSensor keyboard, List<Ball> balls,
                  int velocity, int width, Color color, int start,
                  IconRemover iconRemover, BallAdder ballAdder) {
        //we build a new paddle
        this.keyboard = keyboard;
        this.color = color;
        Rectangle rectangle = new Rectangle(new Point(start, M565), width,
                M15);

        this.paddle = rectangle;

        this.upperLeft = paddle.getUpperLeft();
        this.upperRight =
                new Point(paddle.getUpperLeft().getX() + paddle.getWidth(),
                        paddle.getUpperLeft().getY());
        this.downerLeft = new Point(paddle.getUpperLeft().getX(),
                paddle.getUpperLeft().getY() + paddle.getHeight());
        this.downerRight = new Point(paddle.getUpperLeft().getX()
                + paddle.getWidth(), paddle.getUpperLeft().getY()
                + paddle.getHeight());
        /*we connect the balls to the paddle, so we can tell if one of
        them is inside the paddle*/
        this.balls = balls;
        this.velocity = velocity;
        this.width = width;
        this.iconRemover = iconRemover;
        this.ballAdder = ballAdder;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        //we move the whole template of hte paddle accordingly
        Point p1 = new Point(this.paddle.getUpperLeft().getX() - velocity,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(p1, this.paddle.getWidth(),
                this.paddle.getHeight());

        this.downerLeft = new Point(this.downerLeft.getX() - velocity,
                this.downerLeft.getY());
        this.upperLeft = new Point(this.upperLeft.getX() - velocity,
                this.upperLeft.getY());
        this.downerRight = new Point(this.downerRight.getX() - velocity,
                this.downerRight.getY());
        this.upperRight = new Point(this.upperRight.getX() - velocity,
                this.upperRight.getY());

    }

    /**
     * Move right.
     */
    public void moveRight() {
        //we move the whole template of hte paddle accordingly
        Point p1 = new Point(this.paddle.getUpperLeft().getX() + velocity,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(p1, this.paddle.getWidth(),
                this.paddle.getHeight());

        this.downerLeft = new Point(this.downerLeft.getX() + velocity,
                this.downerLeft.getY());
        this.upperLeft = new Point(this.upperLeft.getX() + velocity,
                this.upperLeft.getY());
        this.downerRight = new Point(this.downerRight.getX() + velocity,
                this.downerRight.getY());
        this.upperRight = new Point(this.upperRight.getX() + velocity,
                this.upperRight.getY());
    }

    // interfaces.Sprite

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    public void timePassed() {
        //we update the paddle due to the time passed
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            //we check if the paddle is on its max screen
            if (getUpperRight().getX() >= 780) {
                return;
            }
            ballsInMeRight();
            moveRight();

        }
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            //we check if the paddle is on its min screen
            if (this.paddle.getUpperLeft().getX() <= 20) {
                return;
            }
            ballsInMeLeft();
            moveLeft();

        }
    }

    /**
     * DrawOn.
     * draw the sprite to the screen
     *
     * @param surface the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        //we draw the paddle
        surface.setColor(this.color);
        surface.fillRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(),
                (int) this.paddle.getHeight());
        //we draw the outline of the paddle
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(),
                (int) paddle.getWidth(),
                (int) paddle.getHeight());
    }

    // interfaces.Collidable

    /**
     * getsCollisionRectangle.
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Hit updated velocity.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit
     * (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter ball point
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        if (hitter instanceof Icon) {
            this.iconRemover.hitEvent(this, (Icon) hitter);
            if (hitter instanceof IconPaddle) {
                if (hitter.getColor() == Color.BLUE) {
                    this.sizeUp(10);
                } else if (hitter.getColor() == Color.GREEN) {
                    this.sizeDown(10);
                }
            }
            if (hitter instanceof IconBalls) {
                ballAdder.hitEvent(this, (Icon) hitter);
            }
        }
        //we first get the corners and the borders of the paddle
        Point upperRight =
                new Point(this.paddle.getUpperLeft().getX()
                        + this.paddle.getWidth(),
                        this.paddle.getUpperLeft().getY());
        Point downerLeft = new Point(this.paddle.getUpperLeft().getX(),
                this.paddle.getUpperLeft().getY()
                        + this.paddle.getHeight());
        Point downerRight = new Point(this.paddle.getUpperLeft().getX()
                + this.paddle.getWidth(),
                upperRight.getY() + this.paddle.getHeight());

        Line top = new Line(upperRight, this.paddle.getUpperLeft());
        Line right = new Line(upperRight, downerRight);
        Line bottom = new Line(downerRight, downerLeft);
        Line left = new Line(this.paddle.getUpperLeft(), downerLeft);
        //we save the current velocity
        Velocity newVel = currentVelocity;
        /*if the collision occurs on the top border we will change the
        velocity accordingly*/
        if (top.pointInLine(collisionPoint)) {
            //x - check which part of the paddle the ball hits
            int x = (int) paddle.getUpperLeft().getX();
            int y = width / 9;
            // we calculate the speed vector
            double vector =
                    Math.pow(currentVelocity.getDx(), M2)
                            + Math.pow(currentVelocity.getDy(), M2);
            double speed = Math.sqrt(vector);
            //we change the velocity as required in the "fun paddle" task
            if (collisionPoint.getX() <= x + y) {
                return Velocity.fromAngleAndSpeed(-M60, speed);
            }
            if (collisionPoint.getX() <= x + 2 * y) {
                return Velocity.fromAngleAndSpeed(-45, speed);
            }
            if (collisionPoint.getX() <= x + 3 * y) {
                return Velocity.fromAngleAndSpeed(-M30, speed);
            }
            if (collisionPoint.getX() <= x + 4 * y) {
                return Velocity.fromAngleAndSpeed(-15, speed);
            }
            if (collisionPoint.getX() <= x + 5 * y) {
                return Velocity.fromAngleAndSpeed(0, speed);
            }
            if (collisionPoint.getX() <= x + 6 * y) {
                return Velocity.fromAngleAndSpeed(15, speed);
            }
            if (collisionPoint.getX() <= x + 7 * y) {
                return Velocity.fromAngleAndSpeed(M30, speed);
            }
            if (collisionPoint.getX() <= x + 8 * y) {
                return Velocity.fromAngleAndSpeed(45, speed);
            }
            if (collisionPoint.getX() <= x + width) {
                return Velocity.fromAngleAndSpeed(M60, speed);
            }
            //we return the updated velocity
            newVel = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        /*if the hit occurs on every other border we treat it like a
        simple block hit*/
        if (right.pointInLine(collisionPoint)) {
            newVel = new Velocity(-newVel.getDx(), newVel.getDy());
        }
        if (left.pointInLine(collisionPoint)) {
            newVel = new Velocity(-newVel.getDx(), newVel.getDy());
        }
        if (bottom.pointInLine(collisionPoint)) {
            newVel = new Velocity(newVel.getDx(), -newVel.getDy());
        }
        //we return the updated velocity
        return newVel;
    }

    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * ballsInMeRight.
     * fix the placement of the ball if it gets to the inside of the
     * paddle when the paddle is moving to the right
     */
    private void ballsInMeRight() {
        for (int i = M0; i < balls.size(); i++) {
            if (balls.get(i).inZone((int) downerRight.getX(),
                    (int) downerRight.getY(),
                    (int) upperLeft.getX(),
                    (int) upperLeft.getY())) {
                balls.get(i).setX(this.upperRight.getX()
                        + balls.get(i).getSize());
                balls.get(i).setY(M565);
            }
        }
    }

    /**
     * ballsInMeLeft.
     * fix the placement of the ball if it gets to the inside of the
     * paddle when the paddle is moving to the left
     */
    private void ballsInMeLeft() {
        for (int i = M0; i < balls.size(); i++) {
            if (balls.get(i).inZone((int) downerRight.getX(),
                    (int) downerRight.getY(),
                    (int) upperLeft.getX(),
                    (int) upperLeft.getY())) {
                balls.get(i).setX(this.upperLeft.getX()
                        - balls.get(i).getSize());
                balls.get(i).setY(M555);
            }
        }
    }
    public void sizeUp (int n) {
        this.width += n;

        this.paddle = new Rectangle(this.upperLeft, width,
                M15);

        this.upperLeft = paddle.getUpperLeft();
        this.upperRight =
                new Point(paddle.getUpperLeft().getX() + paddle.getWidth(),
                        paddle.getUpperLeft().getY());
        this.downerLeft = new Point(paddle.getUpperLeft().getX(),
                paddle.getUpperLeft().getY() + paddle.getHeight());
        this.downerRight = new Point(paddle.getUpperLeft().getX()
                + paddle.getWidth(), paddle.getUpperLeft().getY()
                + paddle.getHeight());
    }

    public void sizeDown (int n) {
        this.width -= n;

        this.paddle = new Rectangle(this.upperLeft, width,
                M15);

        this.upperLeft = paddle.getUpperLeft();
        this.upperRight =
                new Point(paddle.getUpperLeft().getX() + paddle.getWidth(),
                        paddle.getUpperLeft().getY());
        this.downerLeft = new Point(paddle.getUpperLeft().getX(),
                paddle.getUpperLeft().getY() + paddle.getHeight());
        this.downerRight = new Point(paddle.getUpperLeft().getX()
                + paddle.getWidth(), paddle.getUpperLeft().getY()
                + paddle.getHeight());
    }

    /**
     * Add hit listener.
     * add a new listener to this ball
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {

    }

    /**
     * Remove hit listener.
     * remove a listener from this ball
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {

    }
}
