package edu.iis.powp.controllers.ink;

import edu.iis.powp.app.Application;

import java.awt.event.ActionEvent;

public class SetupInkControllerMenu {
    /**
     * Setup menu for ink control.
     *
     * @param application
     *            Application context.
     */
    public static void setupInkControllerMenu(Application application) {
        application.addComponentMenu(InkControllerInterface.class, "Ink Control", 2);
        application.addComponentMenuElement(InkControllerInterface.class, "Refill",
                (ActionEvent e) -> SimmulationInkController.getInstance().fillInk());
        application.addComponentMenuElement(InkControllerInterface.class, "Log ink level",
                (ActionEvent e) -> SimmulationInkController.getInstance().notifyObservators());
    }

}
