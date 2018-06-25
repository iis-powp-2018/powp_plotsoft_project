package edu.iis.powp.controllers.ink;

import edu.iis.powp.app.Application;

import java.awt.event.ActionEvent;

public class SetupInkController {

    /**
     * Setup for ink control.
     *
     * @param application Application context.
     */
    public static void setupInkController(Application application){
        setupInkControllerMenu(application);

        InkControlSubscriber inkControlSubscriber = new InkControlSubscriber(application.getDriverManager());
        application.getDriverManager().getChangePublisher().addSubscriber(inkControlSubscriber);
    }

    private static void setupInkControllerMenu(Application application) {
        InkControllerInterface inkController = SimmulationInkController.getInstance();
        Observed inkControllerObserver = SimmulationInkController.getInstance();

        application.addComponentMenu(InkControllerInterface.class, "Ink Control", 2);
        application.addComponentMenuElement(InkControllerInterface.class, "ON/OFF",
                (ActionEvent e) -> inkController.turnOnOff());
        application.addComponentMenuElement(InkControllerInterface.class, "Refill",
                (ActionEvent e) -> inkController.fillInk());
        application.addComponentMenuElement(InkControllerInterface.class, "Log ink level",
                (ActionEvent e) -> inkControllerObserver.notifyObservators());

    }

}
