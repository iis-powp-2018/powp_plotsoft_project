package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class RotateRightPlotterDecorator extends PlotterDecorator {

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public RotateRightPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    /**
     * This method rotate line by 45 radians and draw it.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        int x = i;
        int y = i1;
        i = (int) (i * Math.cos(Math.toRadians(45)) - i1 * Math.sin(Math.toRadians(45)));
        i1 = (int) (y * Math.cos(Math.toRadians(45)) + x * Math.sin(Math.toRadians(45)));
        originalPlotter.drawTo(i, i1);
    }

    /**
     * This method rotate line by 45 radians and set position of the rotated line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        int x = i;
        int y = i1;
        i = (int) (i * Math.cos(Math.toRadians(45)) - i1 * Math.sin(Math.toRadians(45)));
        i1 = (int) (y * Math.cos(Math.toRadians(45)) + x * Math.sin(Math.toRadians(45)));
        originalPlotter.setPosition(i, i1);
    }
}
