package edu.iis.powp.controllers.ink;

import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

public class LoggingInkInformer implements Observator {
    @Override
    public void inform(float inkLevel) {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(WARNING, String.format("%.2f", inkLevel) +
                                                                        " units of ink remaining.");
    }
}
