package Interfaces;
import GameLogic.*;
import Objects.*;
import RunSystem.*;
public interface HitLisPad extends HitListener {

    void hitEvent(Paddle beingHit, Icon icon);
}
