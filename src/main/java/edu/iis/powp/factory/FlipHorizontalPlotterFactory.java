package edu.iis.powp.factory;

import edu.iis.client.plottermagic.IPlotter;

public class FlipHorizontalPlotterFactory extends PlotterFactory {
    private float scale = -1;

    public FlipHorizontalPlotterFactory(IPlotter originalPlotter) {
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
        originalPlotter.drawTo(i, i1);
    }
}
