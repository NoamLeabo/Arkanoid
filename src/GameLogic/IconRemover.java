// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
/**
 * The type Graphics.Graphics.Ball.Ball remover.
 */
public class IconRemover implements HitLisPad {
    private GameLevel game;


    /**
     * Instantiates a new Graphics.Graphics.Ball.Ball remover.
     *
     * @param game    the game
     */
    public IconRemover(GameLevel game) {
        this.game = game;
    }
    @Override
    public void hitEvent(Paddle beingHit, Icon icon) {
        icon.removeFromGame(game);
    }

    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Graphics.Graphics.Ball.Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit instanceof Border) {
            hitter.removeFromGame(game);
        }
    }
}

