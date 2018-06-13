package edu.iis.powp.factory;

import edu.iis.client.plottermagic.IPlotter;

public class ZoomInPlotterFactory extends PlotterFactory {
    private float scale = (float) 1.5;

    public ZoomInPlotterFactory(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    @Override
    public void drawTo(int i, int i1) {
        i *= scale;
        i1 *= scale;
        originalPlotter.drawTo(i, i1);
    }

    @Override
    public void setPosition(int i, int i1) {
        i *= scale;
        i1 *= scale;
        originalPlotter.drawTo(i, i1);
    }

}
