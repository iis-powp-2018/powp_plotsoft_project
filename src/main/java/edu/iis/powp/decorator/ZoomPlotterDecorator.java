package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class ZoomPlotterDecorator extends PlotterDecorator {

    /**
     * Value of scale.
     */
    private float scale = (float) 2;

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public ZoomPlotterDecorator(IPlotter originalPlotter, String transformation) {
        super(originalPlotter, transformation);
    }

    /**
     * This method scale points by scale and draw line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        if(super.transformation.equalsIgnoreCase("zoomOut")) {
            i /= scale;
            i1 /= scale;
        }
        else if(super.transformation.equalsIgnoreCase("zoomIn")) {
            i *= scale;
            i1 *= scale;
        }
        originalPlotter.drawTo(i, i1);
    }

    /**
     * This method scale points by scale and set position.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        if(super.transformation.equalsIgnoreCase("zoomOut")) {
            i /= scale;
            i1 /= scale;
        }
        else if(super.transformation.equalsIgnoreCase("zoomIn")) {
            i *= scale;
            i1 *= scale;
        }
        originalPlotter.setPosition(i, i1);
    }
}
