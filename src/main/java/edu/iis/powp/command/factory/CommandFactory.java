package edu.iis.powp.command.factory;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import java.util.ArrayList;
import java.util.List;

public final class CommandFactory {

    private final List<IPlotterCommand> commands = new ArrayList<>();

    private CommandFactory(){}

    public static CommandFactory create(){
        return new CommandFactory();
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
        return new ComplexCommand(commands);
    }
}
