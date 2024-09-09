package Objects;
import Interfaces.*;
import GameLogic.*;
import RunSystem.*;

public class BlockBal extends Block{
    /**
     * Instantiates a new Graphics.Block.
     *
     * @param color the color of the block
     */
    public BlockBal(Point upperLeft, Point downerRight,
                    java.awt.Color color) {
        super(upperLeft, downerRight, color);
    }
}
