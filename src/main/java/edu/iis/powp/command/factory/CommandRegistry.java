package edu.iis.powp.command.factory;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class CommandRegistry {

    private Set<Constructor<?>> registeredBasicCommands = new HashSet<>();
    private Map<ICompoundCommand,String> registeredComplexCommands = new HashMap<>();

    public CommandRegistry() {
    }

    public void registerBasicCommand(Class<? extends IPlotterCommand> commandClass, Class ... parameterTypes) throws Throwable {
        Constructor constructor = commandClass.getConstructor(parameterTypes);
        registeredBasicCommands.add(constructor);
    }

    public void deregisterBasicCommand(Constructor<?> command){
        registeredBasicCommands.remove(command);
    }

    public Set<Constructor<?>> getRegisteredBasicCommands() {
        return Collections.unmodifiableSet(registeredBasicCommands);
    }

    public void registerComplexCommand(ICompoundCommand compoundCommand, String name){
        registeredComplexCommands.put(compoundCommand, name);
    }

    public void deregisterComplexCommand(ICompoundCommand compoundCommand){
        registeredComplexCommands.remove(compoundCommand);
    }

    public Map<ICompoundCommand,String> getRegisteredComplexCommands() {
        return Collections.unmodifiableMap(registeredComplexCommands);
    }
}
