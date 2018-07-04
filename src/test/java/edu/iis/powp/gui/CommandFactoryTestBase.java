package edu.iis.powp.gui;

import edu.iis.powp.app.Application;
import edu.iis.powp.command.*;
import edu.iis.powp.command.factory.CommandFactoryWindow;
import edu.iis.powp.command.factory.CommandRegistry;
import edu.iis.powp.command.gui.CommandManagerWindow;
import edu.iis.powp.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CommandFactoryTestBase {

    static void setupCommandFactory(Application application) {

        CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.addComplexCommandRegisterActionListener(addActionListenerForCommands(application));
        try {
            addTestCommand(commandRegistry);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        CommandFactoryWindow window = new CommandFactoryWindow(commandRegistry);
        application.addWindowComponent("Command Factory", window);
    }

    private static void addTestCommand(CommandRegistry commandRegistry) throws Throwable {
        commandRegistry.registerBasicCommand(DrawToCommand.class, int.class, int.class);
        commandRegistry.registerBasicCommand(SetPositionCommand.class, int.class, int.class);
        List<IPlotterCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(0, 0));
        commands.add(new DrawToCommand(10, 10));
        commandRegistry.registerComplexCommand(new ComplexCommand(commands, "Test Command"));
    }



    private static ActionListener addActionListenerForCommands(Application application) {
        return new ActionListener() {

            @Override public void actionPerformed(final ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof ICompoundCommand) {
                    ICompoundCommand command = (ICompoundCommand) source;
                    application.addTest(command.toString(), new ActionListener() {

                        @Override public void actionPerformed(final ActionEvent e) {
                            command.execute(application.getDriverManager().getCurrentPlotter());
                        }
                    });
                }
            }
        };
    }

}
