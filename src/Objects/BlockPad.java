package Objects;
import Interfaces.*;
import java.awt.*;

public class BlockPad extends Block{
    /**
     * Instantiates a new Graphics.Block.
     *
     * @param color the color of the block
     */
    public BlockPad(Point upperLeft, Point downerRight,
                    java.awt.Color color) {
        super(upperLeft, downerRight, color);
    }
}
