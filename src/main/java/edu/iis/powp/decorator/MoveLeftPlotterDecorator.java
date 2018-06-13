package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class MoveLeftPlotterDecorator extends PlotterDecorator {

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public MoveLeftPlotterDecorator(IPlotter originalPlotter) {
        super(originalPlotter);
    }

    /**
     * This method change value of x-axis by -50 and draw line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        i -= 50;
        originalPlotter.drawTo(i, i1);
    }

    /**
     * This method change value of x-axis by -50 and set position.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        i -= 50;
        originalPlotter.setPosition(i, i1);
    }
}
