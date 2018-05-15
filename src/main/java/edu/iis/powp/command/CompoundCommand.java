package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {
    protected List<IPlotterCommand> commands;

    public CompoundCommand() {
        commands = new LinkedList<>();
    }

    public boolean addPlotterCommand(IPlotterCommand command) {
        return commands.add(command);
    }

    public boolean addPlotterCommands(List<IPlotterCommand> commands) {
        return commands.addAll(commands);
    }

    public void addPlotterCommand(IPlotterCommand command, int position) {
        commands.add(position, command);
    }

    public boolean removePlotterCommand(IPlotterCommand command) {
        return commands.remove(command);
    }

    public IPlotterCommand removePlotterCommand(int position) {
        return commands.remove(position);
    }

    public void clearCommandsList() {
        commands.clear();
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(IPlotter plotter) {
        commands.forEach(command -> command.execute(plotter));
    }
}
