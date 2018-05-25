package edu.iis.powp.controllers;

public class SimmulationInkController implements InkControllerInterface {
    private static SimmulationInkController instance = null;
    private int inkLevel;

    private SimmulationInkController(){};

    public static SimmulationInkController getInstance(){
        if (instance == null)
        {
            instance = new SimmulationInkController();
        }
        return instance;
    }

    @Override
    public int getInkLevel() {
        return inkLevel;
    }

    @Override
    public void reduceInkLevel(int level) {
        inkLevel -= level;
    }

    @Override
    public void addInk(int level) {
        inkLevel += level;
    }
}
