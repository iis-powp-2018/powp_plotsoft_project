package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.IPlotterCommand;

public class CompoundCommand implements IPlotterCommand {

	private List<ComplexCommand> complexCommandList = new ArrayList<ComplexCommand>();

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
