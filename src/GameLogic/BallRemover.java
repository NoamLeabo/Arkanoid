// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
/**
 * The type Graphics.Graphics.Ball.Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;


    /**
     * Instantiates a new Graphics.Graphics.Ball.Ball remover.
     *
     * @param game    the game
     * @param counter the counter of the balls remaining in the game
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.remainingBall = counter;
    }

    /**
     * Hit event.
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!(hitter instanceof Icon)) {
            if (beingHit.getUpperLeft().equals(new Point(0, 600))
                    && beingHit.getDownerRight()
                    .equals(new Point(780, 620))) {
                hitter.removeFromGame(game);
                hitter.gameStatus();
                //updating the new number of remaining balls in the game
                remainingBall.decrease(1);
            }
        }
    }
}

