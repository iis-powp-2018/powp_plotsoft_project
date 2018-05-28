package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Implementation of IPlotterCommand for setPosition command functionality.
 */
public class SetPositionCommand implements IPlotterCommand {

	private Coordinates coordinates;

	public SetPositionCommand(int posX, int posY) {
		super();
		coordinates = new Coordinates(posX, posY);
	}
	
	public SetPositionCommand(Coordinates coordinates) {
		super();
		this.coordinates = coordinates;
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.setPosition(coordinates.getPosX(), coordinates.getPosY());
	}

}
