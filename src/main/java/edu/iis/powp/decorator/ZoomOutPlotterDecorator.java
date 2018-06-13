package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class ZoomOutPlotterDecorator extends PlotterDecorator {
    private float scale = (float) 1.5;

    public ZoomOutPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    @Override
    public void drawTo(int i, int i1) {
        i /= scale;
        i1 /= scale;
        originalPlotter.drawTo(i, i1);
    }

    @Override
    public void setPosition(int i, int i1) {
        i /= scale;
        i1 /= scale;
        originalPlotter.setPosition(i, i1);
    }
}
