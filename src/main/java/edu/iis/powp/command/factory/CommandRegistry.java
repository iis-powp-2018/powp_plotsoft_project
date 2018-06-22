package edu.iis.powp.command.factory;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class CommandRegistry {

    private Set<Constructor<? extends IPlotterCommand>> registeredBasicCommands = new HashSet<>();
    private Set<ICompoundCommand> registeredComplexCommands = new HashSet<>();

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
    }

    public void deregisterComplexCommand(ICompoundCommand compoundCommand){
        registeredComplexCommands.remove(compoundCommand);
    }

    public Set<ICompoundCommand> getRegisteredComplexCommands() {
        return Collections.unmodifiableSet(registeredComplexCommands);
    }
}
