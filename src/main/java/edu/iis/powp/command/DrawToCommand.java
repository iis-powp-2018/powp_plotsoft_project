package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Implementation of IPlotterCommand for drawTo command functionality.
 */
public class DrawToCommand extends SimpleCommand {

	public DrawToCommand(int posX, int posY) {
		super(new Coordinates(posX, posY));
	}

	public DrawToCommand(Coordinates coordinates) {
		super(coordinates);
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.drawTo(super.getCoordinates().getPosX(), super.getCoordinates().getPosY());
	}

}
