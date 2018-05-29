package edu.iis.powp.command.complex;

import edu.iis.powp.command.IPlotterCommand;

/**
 * Interface to editing CompundCommand
 */
public interface ICompoundCommandEditor {
	/**
	 * Change sequence of commands
	 * @param index first index to swap
	 * @param indexToSwap second index to swap
	 */
	public void changeSequence(int index, int indexToSwap);
	
	/**
	 *  Removing commmand
	 * @param index Index for romoving command
	 */
	public void removeCommand(int index);
	
	/**
	 * Changing command
	 * @param index index to command to change
	 * @param command Command to swap
	 */
	public void changeCommand(int index, IPlotterCommand command);
}
