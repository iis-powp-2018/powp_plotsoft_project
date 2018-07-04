package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

public class CompoundCommand {
	List<ComplexCommand> list = new ArrayList<ComplexCommand>();

	public void addComplexCommand(ComplexCommand command) {
		list.add(command);
	}

	public void executeOrder66() {
		for (ComplexCommand complexCommand : list) {

		}
	}
}
