package edu.iis.powp.command.manager;

import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.observer.Publisher;

/**
 * Driver Manager.
 */
public class PlotterCommandManager {
	private ICompoundCommand currentCommand = null;
	private String commandName;
	

	private Publisher changePublisher = new Publisher();

	/**
	 * Set current command.
	 * 
	 * @param commandList
	 *            Set the command as current.
	 */
	public synchronized void setCurrentCommand(ICompoundCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	/**
	 * Set current command. Changed anonymous implementation to ComplexCommand
	 * 
	 * @param commandList
	 *            list of commands representing a compound command.
	 * @param name
	 *            name of the command.
	 */
	public synchronized void setCurrentCommand(ICompoundCommand command, String name) {
		setCurrentCommand(command);
		commandName= name;

	}

	/**
	 * Return current command.
	 * 
	 * @return Current command.
	 */
	public synchronized ICompoundCommand getCurrentCommand() {
		return currentCommand;
	}

	public synchronized void clearCurrentCommand() {
		currentCommand = null;
	}

	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return commandName;
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}
}
