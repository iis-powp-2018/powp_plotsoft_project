package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.IPlotterCommand;

public class CompoundCommand implements IPlotterCommand {

	private List<ComplexCommand> complexCommandList;

	/**
	 * constructor to create CompoundCommand
	 * 
	 * @param complexCommandList
	 *            list of complex commands that object will contain
	 */
	public CompoundCommand(List<ComplexCommand> complexCommandList) {
		this.complexCommandList = complexCommandList;
	}

	/**
	 * Add new ComplexCommand to object
	 * 
	 * @param command
	 *            ComplexCommand that will be added
	 */
	public void addComplexCommand(ComplexCommand command) {
		complexCommandList.add(command);
	}

	@Override
	public void execute(IPlotter orderSixtySix) {
		for (ComplexCommand complexCommand : complexCommandList) {
			complexCommand.getListOfCommands().stream().forEach(command -> command.execute(orderSixtySix));
		}

	}

	public List<ComplexCommand> getComplexCommandList() {
		return complexCommandList;
	}
}
