package edu.iis.powp.factory;

import edu.iis.client.plottermagic.IPlotter;

public class FlipVerticalPlotterFactory extends PlotterFactory {
    private float scale = -1;

    public FlipVerticalPlotterFactory(IPlotter originalPlotter) {
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
        originalPlotter.drawTo(i, i1);
    }
}
