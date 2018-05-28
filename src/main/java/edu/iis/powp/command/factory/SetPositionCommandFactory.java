package edu.iis.powp.command.factory;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;


/**
 * Factory to create SetPositionCommand using coordinates pool
 */
public class SetPositionCommandFactory extends SimpleCommandFactory {
	private static SetPositionCommandFactory factory  = new SetPositionCommandFactory(); 
	
	private SetPositionCommandFactory() {};
	
	/**
	 * Returns instance of SetPostionCommandFactory
	 * @return instance of SetPositionCommandFactory
	 */
	public static SetPositionCommandFactory getInstance() {
		return factory;
	}
	
	/**
	 * Create instance of SetPositionCommand
	 * @param coordinates coordinates which we want to have in returning object
	 * @return instance of SetPositionCommand using coordinate from pool
	 */
	@Override
	public IPlotterCommand makeSimpleCommand(Coordinates coordinates) {
		return new SetPositionCommand(checkPool(coordinates));
	}

}
