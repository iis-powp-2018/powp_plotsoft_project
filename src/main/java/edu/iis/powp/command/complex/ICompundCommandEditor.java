package edu.iis.powp.command.complex;

import edu.iis.powp.command.IPlotterCommand;

public interface ICompundCommandEditor {

	/**
	 * Change sequence of commands
	 * 
	 * @param index
	 *            index of first command to swap
	 * @param indexToSwap
	 *            index of second command to swap
	 */
	public void changeSequence(int index, int indexToSwap);

	/**
	 * Add new command to list
	 * 
	 * @param index
	 *            index where we want to add command
	 * @param command
	 *            command to be added
	 */
	public void addCommand(int index, IPlotterCommand command);

	/**
	 * Remove command from sequence
	 * 
	 * @param index
	 *            index of command to remove
	 */
	public void removeCommand(int index);

	/**
	 * @param index
	 *            index of command to change
	 * @param command
	 *            new command
	 */
	public void changeCommand(int index, IPlotterCommand command);
}
