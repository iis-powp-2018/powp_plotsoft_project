package edu.iis.powp.controllers.ink;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * Implementation of Ink Controller for simulation. Singleton design pattern.
 * One unit of ink can be used to draw line of one unit length.
 */
public class SimmulationInkController implements InkControllerInterface, Observed {
	private static SimmulationInkController instance = null;
	private float inkLevel;
	final static float MAX_LEVEL_INK = 1000;
	private ArrayList<Observator> observators;
	private boolean isTurnedOn = false;

	private SimmulationInkController() {
		inkLevel = MAX_LEVEL_INK;
		observators = new ArrayList<>();
	};

	/**
	 * Implementation for Singleton design pattern
	 * 
	 * @return SimmulationInkController instance
	 */
	public static SimmulationInkController getInstance() {
		if (instance == null) {
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
	    if(isTurnedOn) {
            inkLevel -= level;
        }
    }

	@Override
	public void fillInk() {
        if(isTurnedOn) {
            inkLevel = MAX_LEVEL_INK;
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(INFO, "Refilling ink.");
        }
	}

	@Override
	public boolean isInkEnough(float level) {
        if (!isTurnedOn || inkLevel >= level) {
            return true;
        } else {
            return false;
        }
	}

    @Override
    public void turnOnOff() {
        String word;
	    if(isTurnedOn){
            word = "off";
	    }
	    else{
	        word = "on";
        }

        String msg = "Turning " + word + " ink control.";
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(INFO, msg);
        isTurnedOn = !isTurnedOn;
    }

    @Override
	public void addObservator(Observator o) {
		observators.add(o);
	}

	@Override
	public void removeObservator(Observator o) {
		observators.remove(observators.indexOf(o));
	}

	@Override
	public void notifyObservators() {
		for (Observator o : observators) {
			o.inform(inkLevel);
		}
	}
}
