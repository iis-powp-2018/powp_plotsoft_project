package edu.iis.powp.command.io;

import edu.iis.powp.command.complex.ComplexCommand;

/**
 * Input output operation for commands
 * 
 */
public interface ICommandIOOperation {

	/**
	 * Save command to file
	 *
	 * @param command Command to save
	 */
	void save(ComplexCommand command);

	/**
	 * Read complex command from disk
	 *
	 * @return command from disk
	 */
	ComplexCommand read();

}