package edu.iis.powp.command;

public interface ICompoundCommandEditor {
	public boolean changeSequence(int index, int indexToSwap);
	
	public boolean removeCommand(int index);
	
	public boolean changeCommand(int index, IPlotterCommand command);
}
