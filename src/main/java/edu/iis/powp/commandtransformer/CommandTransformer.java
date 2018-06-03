package edu.iis.powp.commandtransformer;

import edu.iis.powp.app.Application;
import edu.iis.powp.commandtransformer.decorator.gui.CommandTransformerCreatorWindow;
import edu.iis.powp.commandtransformer.decorator.gui.components.*;

/**
 * This class is used to setup CommandTransformerCreatorWindow
 */
public class CommandTransformer {

    private static CommandTransformerCreatorWindow commandTransformerCreatorWindow;

    /**
     * This method adds CommandTransformerCreatorWindow to Application passed as parameter
     *
     * @param application the application which CommandTransformerCreatorWindow will be attached to
     */
    public static void setupCommandTransformer(Application application) {
        commandTransformerCreatorWindow = new CommandTransformerCreatorWindow(application.getDriverManager());
        setupBasicWindowComponents();
        application.addWindowComponent("Command Transformer Creator", commandTransformerCreatorWindow);
    }

    /**
     * Setup window components that comes with vanilla CommandTransformer
     */
    private static void setupBasicWindowComponents() {
        commandTransformerCreatorWindow.addComponent("Flip", new FlipCommandComponent());
        commandTransformerCreatorWindow.addComponent("Move", new MoveCommandComponent());
        commandTransformerCreatorWindow.addComponent("Graduate", new GraduationCommandComponent());
        commandTransformerCreatorWindow.addComponent("Stretch", new StretchingCommandComponent());
        commandTransformerCreatorWindow.addComponent("Rotate", new RotationCommandComponent());
    }

    /**
     * Method used to retrieve CommandTransformerCreator window. You can use it to attach your own components to it
     *
     * @return CommandTransformerCreatorWindow
     */
    public static CommandTransformerCreatorWindow getCommandTransformerCreatorWindow() {
        return commandTransformerCreatorWindow;
    }
}
