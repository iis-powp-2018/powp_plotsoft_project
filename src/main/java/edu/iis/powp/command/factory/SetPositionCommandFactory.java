package edu.iis.powp.command.factory;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

public class SetPositionCommandFactory extends SimpleCommandFactory {
	private static SetPositionCommandFactory factory  = new SetPositionCommandFactory(); 
	
	private SetPositionCommandFactory() {};
	
	public static SetPositionCommandFactory getInstance() {
		return factory;
	}
	
	@Override
	public IPlotterCommand makeSimpleCommand(Coordinates coordinates) {
		return new SetPositionCommand(checkPool(coordinates));
	}

}
