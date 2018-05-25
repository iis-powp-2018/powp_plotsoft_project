package edu.iis.powp.controllers.ink;

public interface InkControllerInterface {
    float getInkLevel();
    void reduceInkLevel(float level);
    void fillInk();
    boolean checkInkIsEnough(float level);
}
