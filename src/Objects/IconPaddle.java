package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.Random;

public class IconPaddle extends Ball implements Sprite, Icon{
    private Random random = new Random();
    private Integer status;
    public IconPaddle(Point point){
        super(point, 3, Color.blue);
        this.status = random.nextInt(2) +1;
        if (status == 1) {
            super.setColor(Color.GREEN);
        }
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
        if (status == 2) {
            d.setColor(new Color(0, 255, 255));
        } else if (status == 1) {
            d.setColor(Color.yellow);
        }
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
