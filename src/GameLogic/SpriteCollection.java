// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;

import biuoop.DrawSurface;

import java.util.List;

/**
 * The type interfaces.Sprite collection.
 */
public class SpriteCollection {
    /**
     * The constant M0.
     */
    public static final int M0 = 0;
    private final List<Sprite> sprites;

    /**
     * Instantiates a new interfaces.Sprite collection.
     *
     * @param sprites the sprites
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = M0; i < this.sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(d) on all sprites.
     *
     * @param d the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = M0; i < this.sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}