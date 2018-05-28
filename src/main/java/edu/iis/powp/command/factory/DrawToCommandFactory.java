package edu.iis.powp.command.factory;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;

/**
 * Factory to create DrawToCommand using coordinates pool
 */
public class DrawToCommandFactory extends SimpleCommandFactory {
	private static DrawToCommandFactory factory = new DrawToCommandFactory();
	
	private DrawToCommandFactory() {};
	
	/**
	 * Returns instance of DrawToCommandFactory
	 * @return instance of DrawToCommandFactory
	 */
	public static DrawToCommandFactory getInstance() {
		return factory;
	}

	/**
	 * Create instance of DrawToCommand
	 * @param coordinates coordinates which we want to have in returning object
	 * @return instance of DrawToCommand using coordinate from pool
	 */
	@Override
	public IPlotterCommand makeSimpleCommand(Coordinates coordinates) {
		return new DrawToCommand(checkPool(coordinates));
	}

}
