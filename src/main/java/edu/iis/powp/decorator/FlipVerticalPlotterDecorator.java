package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class FlipVerticalPlotterDecorator extends PlotterDecorator {
    private float scale = -1;

    public FlipVerticalPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    @Override
    public void drawTo(int i, int i1) {
        i1 *= scale;
        originalPlotter.drawTo(i, i1);
    }

    @Override
    public void setPosition(int i, int i1) {
        i1 *= scale;
        originalPlotter.setPosition(i, i1);
    }
}
