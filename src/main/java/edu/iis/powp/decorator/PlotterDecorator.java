package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public abstract class PlotterDecorator implements IPlotter{
    protected IPlotter originalPlotter;
    protected String transformation;

    /**
     * Default constructor.
     * @param originalPlotter instance of IPlotter object.
     */
    public PlotterDecorator(IPlotter originalPlotter, String transformation) {
        this.originalPlotter = originalPlotter;
        this.transformation = transformation;
    }

    /**
     * Abstract implementation of IPlotter interface method.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    public abstract void drawTo(int i, int i1);

    /**
     * Abstract implementation of IPlotter interface method.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    public abstract void setPosition(int i, int i1);


}
