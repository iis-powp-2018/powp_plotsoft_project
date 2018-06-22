package edu.iis.powp.command.factory;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import java.util.ArrayList;
import java.util.List;

public final class CommandFactory {

    private final List<IPlotterCommand> commands = new ArrayList<>();
    private final String name;

    private CommandFactory(final String name){
        this.name = name;
    }

    public static CommandFactory create(String name){
        return new CommandFactory(name);
    }

    public CommandFactory addCommand(IPlotterCommand command){
        commands.add(command);
        return this;
    }

    public CommandFactory removeCommand(int index){
        commands.remove(index);
        return this;
    }

    public ICompoundCommand build(){
        return new ComplexCommand(commands, name);
    }
}
