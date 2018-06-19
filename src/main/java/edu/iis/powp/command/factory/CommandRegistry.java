package edu.iis.powp.command.factory;

import edu.iis.powp.command.IPlotterCommand;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class CommandRegistry {

    private Set<Constructor<?>> registeredCommands = new HashSet<>();

    public CommandRegistry() {
    }

    public void registerCommand(Class<? extends IPlotterCommand> commandClass, Class ... parameterTypes) throws Throwable {
        Constructor constructor = commandClass.getConstructor(parameterTypes);
        registeredCommands.add(constructor);
    }

    public void deregisterCommand(Constructor<?> command){
        registeredCommands.remove(command);
    }

    public Set<Constructor<?>> getRegisteredCommands() {
        return Collections.unmodifiableSet(registeredCommands);
    }
}
