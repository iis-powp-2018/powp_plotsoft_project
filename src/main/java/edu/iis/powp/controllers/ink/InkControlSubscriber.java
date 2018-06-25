package edu.iis.powp.controllers.ink;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.observer.Subscriber;

public class InkControlSubscriber implements Subscriber {

    private DriverManager driverManager;

    public InkControlSubscriber(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void update() {
        IPlotter currentPlotter = driverManager.getCurrentPlotter();
        LinePlotterInkControlDriverDecorator newPlotter = new LinePlotterInkControlDriverDecorator(currentPlotter);

        driverManager.setCurrentPlotter(newPlotter);
    }
}
