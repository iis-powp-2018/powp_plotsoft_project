package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class RotateLeftPlotterDecorator extends PlotterDecorator {

    public RotateLeftPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    @Override
    public void drawTo(int i, int i1) {
        int x = i;
        int y = i1;
        i = (int) (i * Math.cos(Math.toRadians(-45)) - i1 * Math.sin(Math.toRadians(-45)));
        i1 = (int) (y * Math.cos(Math.toRadians(-45)) + x * Math.sin(Math.toRadians(-45)));
        originalPlotter.drawTo(i, i1);
    }

    @Override
    public void setPosition(int i, int i1) {
        int x = i;
        int y = i1;
        i = (int) (i * Math.cos(Math.toRadians(-45)) - i1 * Math.sin(Math.toRadians(-45)));
        i1 = (int) (y * Math.cos(Math.toRadians(-45)) + x * Math.sin(Math.toRadians(-45)));
        originalPlotter.setPosition(i, i1);
    }
}
