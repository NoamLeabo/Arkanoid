package Objects;
import Interfaces.*;// 325764215 Noam Leabovich

/**
 * The type Game.Counter.
 */
public class Counter {
    /**
     * The Game.Counter.
     */
    private int counter;
    // add number to current count.

    /**
     * Instantiates a new Game.Counter.
     *
     * @param initialNum the initial num
     */
    public Counter(int initialNum) {
        this.counter = initialNum;
    }

    /**
     * Increase.
     * increases the counter by a number
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get value int.
     * get current count.
     * @return the int
     */
    public int getValue() {
        return this.counter;
    }
}