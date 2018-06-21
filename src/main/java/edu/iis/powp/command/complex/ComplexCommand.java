package edu.iis.powp.command.complex;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.editor.ICompoundCommandEditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Complex command implements IPlotterCommand
 */
public class ComplexCommand implements ICompoundCommand, ICompoundCommandEditor {

    List<IPlotterCommand> listOfCommands;
    
    /**
     * Constructor to create ComplexCommnad
     * @param listOfCommands List of commands to create
     */
    public ComplexCommand(List<IPlotterCommand> listOfCommands) {
		this.listOfCommands = listOfCommands;
	}
    
    public ComplexCommand(ICompoundCommand command) {
    	Iterator<IPlotterCommand> i = command.iterator();
    	List<IPlotterCommand> commands =  new ArrayList();
    	while(i.hasNext()) {
    		commands.add((IPlotterCommand) i.next());
    	}
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return listOfCommands.iterator();
    }

    @Override
    public void execute(IPlotter plotter) {
        listOfCommands.stream().forEach(command -> command.execute(plotter));
    }
    
	@Override
	public void changeSequence(int index, int indexToSwap) {
		Collections.swap(listOfCommands, index, indexToSwap);
	}

	@Override
	public void removeCommand(int index) {
		listOfCommands.remove(index);
	}

	@Override
	public void changeCommand(int index, IPlotterCommand command) {
		removeCommand(index);
		listOfCommands.add(index,command);
	}
}
