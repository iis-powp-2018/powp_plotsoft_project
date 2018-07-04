package edu.iis.powp.command.factory;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CommandRegistry {

    private Set<Constructor<? extends IPlotterCommand>> registeredBasicCommands = new HashSet<>();
    private Set<ICompoundCommand> registeredComplexCommands = new HashSet<>();
    private List<ActionListener> registerComplexCommandsActionListeners = new ArrayList<>();

    public CommandRegistry() {
    }

    public void registerBasicCommand(Class<? extends IPlotterCommand> commandClass, Class ... parameterTypes) throws Throwable {
        Constructor constructor = commandClass.getConstructor(parameterTypes);
        registeredBasicCommands.add(constructor);
    }

    public void deregisterBasicCommand(Constructor<? extends IPlotterCommand> command){
        registeredBasicCommands.remove(command);
    }

    public Set<Constructor<? extends IPlotterCommand>> getRegisteredBasicCommands() {
        return Collections.unmodifiableSet(registeredBasicCommands);
    }

    public void registerComplexCommand(ICompoundCommand compoundCommand){
        registeredComplexCommands.add(compoundCommand);
        for (ActionListener registerComplexCommandsActionListener : registerComplexCommandsActionListeners) {
            registerComplexCommandsActionListener.actionPerformed(new ActionEvent(compoundCommand, 0, "registerComplexCommand" ));
        }
    }

    public void deregisterComplexCommand(ICompoundCommand compoundCommand){
        registeredComplexCommands.remove(compoundCommand);
    }

    public Set<ICompoundCommand> getRegisteredComplexCommands() {
        return registeredComplexCommands;
    }

    public void addComplexCommandRegisterActionListener(ActionListener actionListener){
        registerComplexCommandsActionListeners.add(actionListener);
    }
}
