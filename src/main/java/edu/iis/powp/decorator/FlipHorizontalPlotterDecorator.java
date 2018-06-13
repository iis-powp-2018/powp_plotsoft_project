package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class FlipHorizontalPlotterDecorator extends PlotterDecorator {
    private float scale = -1;

    public FlipHorizontalPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    @Override
    public void drawTo(int i, int i1) {
        i *= scale;
        originalPlotter.drawTo(i, i1);
    }

    @Override
    public void setPosition(int i, int i1) {
        i *= scale;
        originalPlotter.setPosition(i, i1);
    }
}
