package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Implementation of IPlotterCommand for drawTo command functionality.
 */
public class DrawToCommand implements IPlotterCommand {

	private Coordinates coordinates;

	public DrawToCommand(int posX, int posY) {
		super();
		coordinates = new Coordinates(posX, posY);
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.drawTo(coordinates.getPosX(), coordinates.getPosY());
	}

}
