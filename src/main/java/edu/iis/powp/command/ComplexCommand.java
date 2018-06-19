package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import java.util.Iterator;
import java.util.List;

public final class ComplexCommand implements ICompoundCommand {

    private final List<IPlotterCommand> commands;

    public ComplexCommand(final List<IPlotterCommand> commands) {
        this.commands = commands;
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(final IPlotter plotter) {
        for (IPlotterCommand command : commands) {
            command.execute(plotter);
        }

    }
}
