package edu.iis.powp.commandtransformer;

import edu.iis.powp.app.Application;
import edu.iis.powp.decorator.gui.CommandTransformerCreatorWindow;
import edu.iis.powp.decorator.gui.components.*;

public class CommandTransformer {
    private static CommandTransformerCreatorWindow commandTransformerCreatorWindow;

    public static void setupCommandTransformer(Application application) {
        commandTransformerCreatorWindow = new CommandTransformerCreatorWindow(application.getDriverManager());
        setupBasicWindowComponents();
        application.addWindowComponent("Command Transformer Creator", commandTransformerCreatorWindow);
    }

    private static void setupBasicWindowComponents() {
        commandTransformerCreatorWindow.addComponent(new FlipCommandComponent());
        commandTransformerCreatorWindow.addComponent(new MoveCommandComponent());
        commandTransformerCreatorWindow.addComponent(new GraduationCommandComponent());
        commandTransformerCreatorWindow.addComponent(new StretchingCommandComponent());
        commandTransformerCreatorWindow.addComponent(new RotationCommandComponent());
    }

    public static CommandTransformerCreatorWindow getCommandTransformerCreatorWindow() {
        return commandTransformerCreatorWindow;
    }
}
