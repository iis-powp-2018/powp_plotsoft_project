package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

public class CompoundCommand {
	List<ArrayList<ComplexCommand>> list = new ArrayList<ArrayList<ComplexCommand>>();

	public void addComplexCommand(ArrayList<ComplexCommand> command) {
		list.add(command);
	}

	public void executeOrder66() {
		for (ArrayList<ComplexCommand> arrayList : list) {
			for (ComplexCommand complexCommand : arrayList) {
			}
		}
	}
}
