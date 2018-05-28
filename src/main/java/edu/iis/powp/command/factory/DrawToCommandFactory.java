package edu.iis.powp.command.factory;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;

public class DrawToCommandFactory extends SimpleCommandFactory {
	private static DrawToCommandFactory factory = new DrawToCommandFactory();
	
	private DrawToCommandFactory() {};
	
	public static DrawToCommandFactory getInstance() {
		return factory;
	}

	@Override
	public IPlotterCommand makeSimpleCommand(Coordinates coordinates) {
		return new DrawToCommand(checkPool(coordinates));
	}

}
