package edu.iis.powp.command.complex;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

public class ComplexCommand implements ICompoundCommand, ICompundCommandEditor {

	List<IPlotterCommand> listOfCommands;

	/**
	 * Constructor to create ComplexCommnad
	 * 
	 * @param listOfCommands
	 *            List of commands ComplexCommand will contain
	 */
	public ComplexCommand(List<IPlotterCommand> listOfCommands) {
		this.listOfCommands = listOfCommands;
	}

	@Override
	public void execute(IPlotter plotter) {
		listOfCommands.stream().forEach(command -> command.execute(plotter));
	}

	@Override
	public Iterator<IPlotterCommand> iterator() {
		return listOfCommands.iterator();
	}

	@Override
	public void changeSequence(int index, int indexToSwap) {
		Collections.swap(listOfCommands, index, indexToSwap);
	}

	@Override
	public void addCommand(int index, IPlotterCommand command) {
		listOfCommands.add(index, command);
	}

	@Override
	public void removeCommand(int index) {
		listOfCommands.remove(index);
	}

	@Override
	public void changeCommand(int index, IPlotterCommand command) {
		removeCommand(index);
		addCommand(index, command);
	}

}
