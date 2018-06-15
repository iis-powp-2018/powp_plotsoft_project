package edu.iis.powp.command.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

/**
 * Abstract factory for commands containing coordinates 
 */
public class IPlotterCommandFactory {
	private static HashMap<String,IPlotterCommand> commandsPool = new HashMap<>();
	
	/**
	 *  Checking if passed coordinates are in coordinates pool, if not they are add to pool
	 * @param coordinates Coordinates to check
	 * @return pointer to coordinates from pool
	 */
	private IPlotterCommand checkPool(String commandId) {
		return commandsPool.get(commandId);			
	}
	
	/**
	 * Create IPlotterCommand using coordinates from pool
	 * @param coordinates which we want to have in returning object
	 * @return IPlotterCommand using coordinates form pool
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
