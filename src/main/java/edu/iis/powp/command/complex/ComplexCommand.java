package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

public class ComplexCommand implements ICompoundCommand, IComplexCommandEditor {

	private List<IPlotterCommand> listOfCommands;

	/**
	 * Constructor to create ComplexCommnad
	 * 
	 * @param listOfCommands
	 *            listOfCommands List of commands ComplexCommand will contain
	 */

	public ComplexCommand(List<IPlotterCommand> listOfCommands) {
		this.listOfCommands = listOfCommands;
	}

	public ComplexCommand(ICompoundCommand command) {
		List<IPlotterCommand> commands = new ArrayList();
		if (command == null)
			return;
		Iterator<IPlotterCommand> i = command.iterator();
		while (i.hasNext()) {
			commands.add((IPlotterCommand) i.next());
		}
		listOfCommands = commands;
	}

	@Override
	public void execute(IPlotter plotter) {
		getListOfCommands().stream().forEach(command -> command.execute(plotter));
	}

	@Override
	public Iterator<IPlotterCommand> iterator() {
		return getListOfCommands().iterator();
	}

	@Override
	public void changeSequence(int index, int indexToSwap) {
		Collections.swap(getListOfCommands(), index, indexToSwap);
	}

	@Override
	public void addCommand(int index, IPlotterCommand command) {
		getListOfCommands().add(index, command);
	}

	@Override
	public void removeCommand(int index) {
		getListOfCommands().remove(index);
	}

	@Override
	public void changeCommand(int index, IPlotterCommand command) {
		removeCommand(index);
		addCommand(index, command);
	}

	public List<IPlotterCommand> getListOfCommands() {
		return listOfCommands;
	}

}
