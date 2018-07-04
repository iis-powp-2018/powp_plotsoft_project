package edu.iis.powp.controllers.ink;

/**
 * Interface for controlling ink.
 */
public interface InkControllerInterface {
    /**
     * Getter for ink level.
     * @return float ink level
     */
    float getInkLevel();

    /**
     * Reduce ink level by given number.
     * @param level - number by which to reduce
     */
    void reduceInkLevel(float level);

    /**
     * Fills ink to maximum.
     */
    void fillInk();

    /**
     * Checks if enough ink for drawing.
     * @param level to check
     * @return true if there is enough ink, false if there is not
     */
    boolean isInkEnough(float level);

    /**
     * Turns on and off ink control.
     */
    void turnOnOff();
}
