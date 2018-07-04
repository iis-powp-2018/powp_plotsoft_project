package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class FlipPlotterDecorator extends PlotterDecorator {
    /**
     * For inverting points horizontal.
     */
    private float scale = -1;

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public FlipPlotterDecorator(IPlotter originalPlotter, String transformation) {
        super(originalPlotter, transformation);
    }

    /**
     * This method invert value of x-axis and draw line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        if(super.transformation.equalsIgnoreCase("flipHorizontal"))
            i *= scale;
        else if (super.transformation.equalsIgnoreCase("flipVertical"))
            i1 *= scale;
        originalPlotter.drawTo(i, i1);
    }

    /**
     * This method invert value of x-axis and set position.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        if(super.transformation.equalsIgnoreCase("flipHorizontal"))
            i *= scale;
        else if (super.transformation.equalsIgnoreCase("flipVertical"))
            i1 *= scale;
        originalPlotter.setPosition(i, i1);
    }
}
