package edu.iis.powp.command.complex;

import edu.iis.powp.command.IPlotterCommand;

public interface ICompoundCommandEditor {
	public void changeSequence(int index, int indexToSwap);
	
	public void removeCommand(int index);
	
	public void changeCommand(int index, IPlotterCommand command);
}
