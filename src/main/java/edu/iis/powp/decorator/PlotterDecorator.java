package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public abstract class PlotterDecorator implements IPlotter{
    protected IPlotter originalPlotter;

    public PlotterDecorator(IPlotter originalPlotter) {
        this.originalPlotter = originalPlotter;
    }

    public abstract void drawTo(int i, int i1);
    public abstract void setPosition(int i, int i1);

}
