package edu.iis.powp.command.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

/**
 * Factory for IPlotterCommands
 */
public class IPlotterCommandFactory {
	private static HashMap<String,IPlotterCommand> commandsPool = new HashMap<>();
	
	/**
	 *  Checking if passed command is in commands pool, if not it returns null
	 * @param commandId string which describe command
	 * @return pointer to command from pool
	 */
	private IPlotterCommand checkPool(String commandId) {
		return commandsPool.get(commandId);			
	}
	
	/**
	 * Create SetPositionCommand using command from pool, if it is not existing in pool it is created
	 * @param x x position
	 * @param y y position
	 * @return instance of SetPositionCommand
	 */
	public SetPositionCommand makeSetPositionCommand(int x, int y) {
		String commandId = "SetPositionCommand" + String.valueOf(x) + String.valueOf(y);
		
		SetPositionCommand instance = (SetPositionCommand) checkPool(commandId);
		if(instance == null) {
			instance = new SetPositionCommand(x, y);
			commandsPool.put(commandId, instance);
		}
		
		return instance;	
	}
	
	/**
	 * Create DrawToCommand using command from pool, if it is not existing in pool it is created
	 * @param x x position
	 * @param y y position
	 * @return instance of DrawToCommand
	 */
	public DrawToCommand makeDrawToCommand(int x, int y) {
		String commandId = "DrawToCommand" + String.valueOf(x) + String.valueOf(y);
		
		DrawToCommand instance = (DrawToCommand) checkPool(commandId);
		if(instance == null) {
			instance = new DrawToCommand(x, y);
			commandsPool.put(commandId, instance);
		}
		
		return instance;	
	}
	
}
