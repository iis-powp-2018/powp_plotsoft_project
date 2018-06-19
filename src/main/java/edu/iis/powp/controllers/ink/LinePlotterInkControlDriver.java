package edu.iis.powp.controllers.ink;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;

public class LinePlotterInkControlDriver implements IPlotter {

    private LineAdapterPlotterDriver plotter;
    private InkControllerInterface inkController;

    public LinePlotterInkControlDriver(LineAdapterPlotterDriver plotter) {
        this.plotter = plotter;
        this.inkController = SimmulationInkController.getInstance();
    }

    @Override
    public void setPosition(int i, int i1) {
        plotter.setPosition(i, i1);
    }

    @Override
    public void drawTo(int x, int y) {
        float lineLength = calculateLineLength(plotter.getStartX(), plotter.getStartY(), x, y);
        boolean enoughInk = inkController.checkInkIsEnough(lineLength);

        if(enoughInk) {
            plotter.getLine().setStartCoordinates(plotter.getStartX(), plotter.getStartY());
            this.setPosition(x, y);
            plotter.getLine().setEndCoordinates(x, y);
            inkController.reduceInkLevel(lineLength);
            plotter.getDrawController().drawLine(plotter.getLine());
        }
    }

    private float calculateLineLength(int startX, int startY, int x, int y) {
        return (float) Math.sqrt((startX - x) * (startX - x) + (startY - y) * (startY - y));
    }
}
