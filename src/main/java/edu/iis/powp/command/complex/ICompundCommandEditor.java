package edu.iis.powp.command.complex;

public interface ICompundCommandEditor {
	
	/**
	 * Change sequence of commands
	 * @param index index of first command to swap
	 * @param indexToSwap index of second command to swap
	 */
	public void changeSequence(int index, int indexToSwap);
}
