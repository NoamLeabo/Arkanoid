package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;
import biuoop.DrawSurface;

import java.awt.*;

public class IconBalls extends Ball implements Sprite, Icon {
    public IconBalls(Point point){
        super(point, 3, Color.RED);
        super.setVelocity(0, 2);
    }

    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255,0,255));
        d.fillCircle(super.getX(), super.getY(), 5);
        super.drawOn(d);
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

        Point end = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, end);
        CollisionInfo collisionInfo =
                this.gameEnvironment.getClosestCollision111(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Velocity velocity1 =
                collisionInfo.collisionObject()
                        .hit(this, collisionInfo.collisionPoint(),
                                this.velocity);
        super.center = this.getVelocity().applyToPoint(this.center);
    }
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
