package edu.iis.powp.controllers.ink;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;

import java.util.logging.Logger;

import static edu.iis.powp.controllers.ink.CalculateLineLength.calculateLineLength;
import static java.util.logging.Level.WARNING;

public class LinePlotterInkControlDriver implements IPlotter {

    private LineAdapterPlotterDriver plotter;
    private InkControllerInterface inkController;
    private Observed inkControllerObserved;

    public LinePlotterInkControlDriver(LineAdapterPlotterDriver plotter) {
        this.plotter = plotter;
        this.inkController = SimmulationInkController.getInstance();
        this.inkControllerObserved = SimmulationInkController.getInstance();
        this.inkControllerObserved.addObservator(new InkInformer());
    }

    @Override
    public void setPosition(int i, int i1) {
        plotter.setPosition(i, i1);
    }

    @Override
    public void drawTo(int x, int y) {
        float lineLength = calculateLineLength(plotter.getStartX(), plotter.getStartY(), x, y);
        boolean enoughInk = inkController.isInkEnough(lineLength);

        if(enoughInk) {
            plotter.getLine().setStartCoordinates(plotter.getStartX(), plotter.getStartY());
            this.setPosition(x, y);
            plotter.getLine().setEndCoordinates(x, y);
            inkController.reduceInkLevel(lineLength);
            plotter.getDrawController().drawLine(plotter.getLine());
        }
        else {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(WARNING,
                                                            "Ink Level on too low level, impossible to draw.");
            inkControllerObserved.notifyObservators();
        }
    }
}
