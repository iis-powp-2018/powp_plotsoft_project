package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.IPlotterCommand;

public class CompoundCommand implements IPlotterCommand {

	List<ComplexCommand> list = new ArrayList<ComplexCommand>();

	public void addComplexCommand(ComplexCommand command) {
		list.add(command);
	}

	@Override
	public void execute(IPlotter orderSixtySix) {
		for (ComplexCommand complexCommand : list) {
			complexCommand.getListOfCommands().stream().forEach(command -> command.execute(orderSixtySix));
		}

	}
}
