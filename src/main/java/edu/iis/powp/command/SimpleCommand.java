package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Simple command which contains coordinates
 */
public abstract class SimpleCommand implements IPlotterCommand {
	protected Coordinates coordinates;
	
	protected SimpleCommand(Coordinates coordinates){
		this.coordinates = coordinates;
	}
	/**
	 * Return coordinates
	 * @return coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

}
