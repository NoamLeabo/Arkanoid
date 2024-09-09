// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import Objects.Point;
import RunSystem.*;

import java.awt.*;

/**
 * The type Graphics.Block remover.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;
    private IconRemover iconRemover;
    private GameEnvironment gameEnvironment;


    /**
     * Instantiates a new Graphics.Block remover.
     *
     * @param game        the game
     * @param counter     the counter
     * @param iconRemover
     * @param environment
     */
    public BlockRemover(GameLevel game, Counter counter,
                        IconRemover iconRemover,
                        GameEnvironment environment) {
        this.game = game;
        this.remainingBlocks = counter;
        this.iconRemover = iconRemover;
        this.gameEnvironment = environment;
    }

    /**
     * Hit event.
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!(hitter instanceof Icon)) {
            if (beingHit.getHits() == 1) {
                //making sure the block is not a border
                //remove the block being hit from the game
                beingHit.removeFromGame(game);
                //updating the new number of remaining block in the game
                remainingBlocks.decrease(1);
                //updating the new number of remaining blocks in each line
            } else if (beingHit.getHits() == 2) {
                beingHit.hitted();
                beingHit.setColor(beingHit.getSecColor());
            } else {
                beingHit.hitted();
            }

            if (beingHit instanceof BlockPad) {
                Point p = new Point(beingHit.getUpperLeft().getX() + 25,
                        beingHit.getUpperLeft().getY());
                IconPaddle iconPaddle = new IconPaddle(p);
                game.addSprite(iconPaddle);
                iconPaddle.setGameEnvironment(this.gameEnvironment);

            }
            if (beingHit instanceof BlockBal) {
                Point p = new Point(beingHit.getUpperLeft().getX() + 25,
                        beingHit.getUpperLeft().getY());
                IconBalls iconBalls = new IconBalls(p);
                game.addSprite(iconBalls);
                iconBalls.setGameEnvironment(this.gameEnvironment);
            }
        }
    }
}
