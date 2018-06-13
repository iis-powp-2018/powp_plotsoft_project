package edu.iis.powp.features;

import edu.iis.client.plottermagic.IPlotter;

public class PlotterDecorator implements IPlotter {
    private float scale = 1;
    private IPlotter originalPlotter;

    public PlotterDecorator(IPlotter originalPlotter) {
        this.originalPlotter = originalPlotter;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
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
        originalPlotter.setPosition(i, i1);
    }
}