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
        commandTransformerCreatorWindow.addComponent("Flip", new FlipCommandComponent());
        commandTransformerCreatorWindow.addComponent("Move", new MoveCommandComponent());
        commandTransformerCreatorWindow.addComponent("Graduate", new GraduationCommandComponent());
        commandTransformerCreatorWindow.addComponent("Stretch", new StretchingCommandComponent());
        commandTransformerCreatorWindow.addComponent("Rotate", new RotationCommandComponent());
    }

    public static CommandTransformerCreatorWindow getCommandTransformerCreatorWindow() {
        return commandTransformerCreatorWindow;
    }
}
