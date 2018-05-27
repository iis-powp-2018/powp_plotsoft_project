package edu.iis.powp.command.complex;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {

    List<IPlotterCommand> listOfCommands = new ArrayList<>();

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return listOfCommands.iterator();
    }

    @Override
    public void execute(IPlotter plotter) {
        listOfCommands.stream().forEach(command -> command.execute(plotter));
    }
}
