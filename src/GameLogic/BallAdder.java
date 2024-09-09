// 325764215 Noam Leabovich
package GameLogic;
import Interfaces.*;
import Objects.*;
import RunSystem.*;
import java.util.List;
import java.util.Random;

/**
 * The type Graphics.Graphics.Ball.Ball remover.
 */
public class BallAdder implements HitLisPad {
    private GameLevel game;
    private Counter remainingBall;
    private List<Ball> balls;
    private GameEnvironment environment;
    private BallRemover ballRemover;

    /**
     * Instantiates a new Graphics.Graphics.Ball.Ball remover.
     *
     * @param game        the game
     * @param counter     the counter of the balls remaining in the game
     * @param balls
     * @param environment
     * @param ballRemover
     */
    public BallAdder(GameLevel game, Counter counter, List<Ball> balls,
                     GameEnvironment environment, BallRemover ballRemover) {
        this.game = game;
        this.remainingBall = counter;
        this.balls = balls;
        this.ballRemover = ballRemover;
        this.environment = environment;
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
    }

    @Override
    public void hitEvent(Paddle beingHit, Icon icon) {
        if (icon instanceof IconBalls) {
            Random random = new Random();
            int i;
            do {
                i = random.nextInt(this.balls.size());
            } while (this.balls.get(i).getY() >= 570);
            Ball ball =
                    new Ball(balls.get(i).getCenter(), balls.get(i).getRadius(),
                            balls.get(i).getColor());
            //if (balls.get(i).getVelocity().getDy() > 0) {
            //    ball.setVelocity(-balls.get(i).getVelocity().getDx(),
            //            -balls.get(i).getVelocity().getDy());
           // } else {
           //     ball.setVelocity(-balls.get(i).getVelocity().getDx(),
          //              balls.get(i).getVelocity().getDy());
         //   }
            {
                double vector =
                        Math.pow(balls.get(i).getVelocity().getDx(), 2)
                                + Math.pow(
                                balls.get(i).getVelocity().getDy(),
                                2);
                double speed = Math.sqrt(vector);
                Velocity newV =
                        Velocity.fromAngleAndSpeed(random.nextInt(60) + 30
                                , speed);

            ball.setVelocity(newV);
            }
            ball.addToGame(game);
            ball.addHitListener(ballRemover);
            ball.setGameEnvironment(environment);
            this.remainingBall.increase(1);
            this.balls.add(ball);
        }
    }
}

