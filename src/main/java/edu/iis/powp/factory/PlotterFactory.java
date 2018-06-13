package edu.iis.powp.factory;

import edu.iis.client.plottermagic.IPlotter;

public abstract class PlotterFactory implements IPlotter{
    protected IPlotter originalPlotter;

    public PlotterFactory(IPlotter originalPlotter) {
        this.originalPlotter = originalPlotter;
    }

    public abstract void drawTo(int i, int i1);
    public abstract void setPosition(int i, int i1);

}
