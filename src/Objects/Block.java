// 325764215 Noam Leabovich
package Objects;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;


/**
 * The type Graphics.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The Upper left.
     */
    private final Point upperLeft;
    /**
     * The Upper right.
     */
    private final Point upperRight;
    /**
     * The Downer left.
     */
    private final Point downerLeft;
    /**
     * The Downer right.
     */
    private final Point downerRight;
    private final Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private int hits;
    private java.awt.Color secColor;


    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Gets downer right.
     *
     * @return the downer right
     */
    public Point getDownerRight() {
        return this.downerRight;
    }

    /**
     * Instantiates a new Graphics.Block.
     *
     * @param block the rectangle as the new block
     * @param color the color of the block
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.block = block;
        //to make it more comfortable we get the 4 points of the block
        this.upperLeft = block.getUpperLeft();
        this.upperRight =
                new Point(block.getUpperLeft().getX() + block.getWidth(),
                        block.getUpperLeft().getY());
        this.downerLeft = new Point(block.getUpperLeft().getX(),
                block.getUpperLeft().getY() + block.getHeight());
        this.downerRight = new Point(block.getUpperLeft().getX()
                + block.getWidth(), block.getUpperLeft().getY()
                + block.getHeight());

        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.hits = 1;
    }

    /**
     * Instantiates a new Graphics.Block from 2 opposite dots .
     *
     * @param upperLeft   the upperLeft point
     * @param downerRight the downerRight point
     * @param color       the color
     */
    public Block(Point upperLeft, Point downerRight,
                 java.awt.Color color) {
        Rectangle rectangle = new Rectangle(upperLeft,
                downerRight.getX() - upperLeft.getX(),
                downerRight.getY() - upperLeft.getY());
        this.block = rectangle;
        //same here we get the points' values
        this.upperLeft = block.getUpperLeft();
        this.upperRight =
                new Point(block.getUpperLeft().getX() + block.getWidth(),
                        block.getUpperLeft().getY());
        this.downerLeft = new Point(block.getUpperLeft().getX(),
                block.getUpperLeft().getY() + block.getHeight());
        this.downerRight = new Point(block.getUpperLeft().getX()
                + block.getWidth(), block.getUpperLeft().getY()
                + block.getHeight());
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.hits = 1;
    }

    public Color getSecColor() {
        return this.secColor;
    }

    public void setSecColor(Color secColor) {
        this.secColor = secColor;
    }

    public int getHits() {
        return hits;
    }
    public void hitted(){
        this.hits -= 1;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setHits(int n) {
        this.hits = n;
    }

    /**
     * DrawOn.
     * draw the sprite to the screen
     *
     * @param surface the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        //we set the block's color
        surface.setColor(this.color);
        //first we fill the block's inside
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
        //we set the outline
        surface.setColor(Color.BLACK);
        //we draw the outline
        surface.drawRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
    }

    @Override
    public void timePassed() {
    }


    /**
     * Add to game.
     * we add the block to the game's lists - as a interfaces.Sprite and a interfaces.Collidable
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        //we set the "boarders" of the block as lines
        Line top = new Line(this.upperRight, this.block.getUpperLeft());
        Line right = new Line(this.upperRight, this.downerRight);
        Line bottom = new Line(this.downerRight, this.downerLeft);
        Line left = new Line(this.block.getUpperLeft(), this.downerLeft);
        //we get the current velocity
        Velocity newVel = currentVelocity;

        /*for each case of a collision with a boarder we'll change the
        velocity accordingly*/

        if (top.pointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDx(),
                    -Math.abs(currentVelocity.getDy()));
        }
        if (right.pointInLine(collisionPoint)) {
            newVel = new Velocity(Math.abs(newVel.getDx()),
                    newVel.getDy());
        }
        if (left.pointInLine(collisionPoint)) {
            newVel = new Velocity(-Math.abs(newVel.getDx()),
                    newVel.getDy());
        }
        if (bottom.pointInLine(collisionPoint)) {
            newVel = new Velocity(newVel.getDx(),
                    Math.abs(newVel.getDy()));
        }
        //notify all the listeners about the hit
        this.notifyHit(hitter);
        //we return the updated velocity
        return newVel;

    }

    private void notifyHit(Ball hitter) {
        //Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        //Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove from game.
     * remove this block from the game
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add hit listener.
     * add a new listener to this block
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     * remove a listener from th block
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
