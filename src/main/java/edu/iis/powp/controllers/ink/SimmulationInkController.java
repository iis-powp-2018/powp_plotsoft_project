package edu.iis.powp.controllers.ink;

public class SimmulationInkController implements InkControllerInterface {
    private static SimmulationInkController instance = null;
    private float inkLevel;
    private float MAX_LEVEL_INK = 1000;

    private SimmulationInkController(){
        inkLevel = MAX_LEVEL_INK;
    };

    public static SimmulationInkController getInstance(){
        if (instance == null)
        {
            instance = new SimmulationInkController();
        }
        return instance;
    }

    @Override
    public float getInkLevel() {
        return inkLevel;
    }

    @Override
    public void reduceInkLevel(float level) {
        inkLevel -= level;
    }

    @Override
    public void fillInk() {
        inkLevel = MAX_LEVEL_INK;
    }

    @Override
    public boolean checkInkIsEnough(float level) {
        if (inkLevel >= level) {
            return true;
        }
        else {
            return false;
        }
    }

}
