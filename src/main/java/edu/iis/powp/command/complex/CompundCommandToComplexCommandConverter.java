package edu.iis.powp.command.complex;

import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.command.IPlotterCommand;

public class CompundCommandToComplexCommandConverter {
	/**
	 * @param compCmd
	 *            object to be converted to complex command
	 * @return complex command containing all commands from compCmd
	 */
	public static ComplexCommand compoundToComplex(CompoundCommand compCmd) {
		ComplexCommand temp = null;
		List<ComplexCommand> tempList = new ArrayList<ComplexCommand>(compCmd.getComplexCommandList());
		List<IPlotterCommand> tempListIPlot = new ArrayList<IPlotterCommand>();
		for (ComplexCommand cc : tempList) {
			tempListIPlot.addAll(cc.getListOfCommands());
		}
		temp = new ComplexCommand(tempListIPlot);
		return temp;
	}
}
