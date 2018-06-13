package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class FlipHorizontalPlotterDecorator extends PlotterDecorator {
    /**
     * For inverting points horizontal.
     */
    private float scale = -1;

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public FlipHorizontalPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    /**
     * This method invert value of x-axis and draw line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        i *= scale;
        originalPlotter.drawTo(i, i1);
    }

    /**
     * This method invert value of x-axis and set position.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        i *= scale;
        originalPlotter.setPosition(i, i1);
    }
}
