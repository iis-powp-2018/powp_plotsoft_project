package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

public abstract class SimpleCommand implements IPlotterCommand {
	protected Coordinates coordinates;
	
	protected SimpleCommand(Coordinates coordinates){
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

}
