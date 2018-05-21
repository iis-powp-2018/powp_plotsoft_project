package edu.iis.powp.commandtransformer;

import edu.iis.powp.app.Application;
import edu.iis.powp.decorator.gui.CommandTransformerCreatorWindow;
import edu.iis.powp.features.CommandsFeature;

public class CommandTransformer {
    public static void setupCommandTransformer(Application application) {
        CommandTransformerCreatorWindow commandTransformerCreatorWindow =
                new CommandTransformerCreatorWindow(application.getDriverManager(), CommandsFeature.getPlotterCommandManager());
        application.addWindowComponent("Command Transformer Creator", commandTransformerCreatorWindow);
    }
}
