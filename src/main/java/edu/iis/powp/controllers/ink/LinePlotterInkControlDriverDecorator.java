package edu.iis.powp.controllers.ink;

import edu.iis.client.plottermagic.IPlotter;

import java.util.logging.Logger;

import static edu.iis.powp.controllers.ink.CalculateLineLength.calculateLineLength;
import static java.util.logging.Level.WARNING;

public class LinePlotterInkControlDriverDecorator implements IPlotter {

    private IPlotter plotter;
    private InkControllerInterface inkController;
    private Observed inkControllerObserved;
    private int startX = 0, startY = 0;

    public LinePlotterInkControlDriverDecorator(IPlotter plotter) {
        this.plotter = plotter;
        this.inkController = SimmulationInkController.getInstance();
        this.inkControllerObserved = SimmulationInkController.getInstance();
    }

    @Override
    public void setPosition(int i, int i1) {
        plotter.setPosition(i, i1);
        this.startX = i;
        this.startY = i1;
    }

    @Override
    public void drawTo(int x, int y) {
        float lineLength = calculateLineLength(startX, startY, x, y);
        boolean enoughInk = inkController.isInkEnough(lineLength);

        if(enoughInk) {
            inkController.reduceInkLevel(lineLength);
            plotter.drawTo(x, y);
        }
        else {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(WARNING,
                                                            "Ink Level on too low level, impossible to draw.");
            inkControllerObserved.notifyObservators();
        }
    }
}
