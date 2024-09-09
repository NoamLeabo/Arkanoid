package RunSystem;// 325764215 Noam Leabovich
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Interfaces.*;
import GameLogic.*;
import Objects.*;
/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key detected
     * @param animation the animation running
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.key = key;
        this.animation = animation;
        this.keyboard = sensor;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //we shoe the animation
        this.animation.doOneFrame(d);
        //if the key is already pressed, we ignore it
        if (isAlreadyPressed) {
            isAlreadyPressed =
                    this.keyboard.isPressed(KeyboardSensor.SPACE_KEY);
        }
        //otherwise..
        if (!isAlreadyPressed) {
            //we check if it's the key we are looking for
            if (this.key.matches("space")) {
                if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                    //we stop the animation
                    this.stop =
                            true;
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}