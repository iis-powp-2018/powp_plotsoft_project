package edu.iis.powp.controllers.ink;

import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

public class InkInformer implements Observator {
    @Override
    public void inform(float inkLevel) {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(WARNING, "Ink Level on too low level, impossible to draw." +
                                                                        "\n\tOnly " + inkLevel + " units remaining");
    }
}
