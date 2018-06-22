package edu.iis.powp.commandtransformer;

import edu.iis.powp.app.Application;
import edu.iis.powp.commandtransformer.decorator.gui.CommandTransformerCreatorWindow;
import edu.iis.powp.commandtransformer.decorator.gui.components.*;
import edu.iis.powp.commandtransformer.decorator.gui.events.*;

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
        commandTransformerCreatorWindow.addComponent("Flip", new FlipCommandComponent(new ApplyFlipCommandListener()));
        commandTransformerCreatorWindow.addComponent("Move", new MoveCommandComponent(new ApplyMoveCommandListener()));
        commandTransformerCreatorWindow.addComponent("Graduate", new GraduationCommandComponent(new ApplyGraduationCommandListener()));
        commandTransformerCreatorWindow.addComponent("Stretch", new StretchingCommandComponent(new ApplyStretchingCommandListener()));
        commandTransformerCreatorWindow.addComponent("Rotate", new RotationCommandComponent(new ApplyRotationCommandListener()));
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
