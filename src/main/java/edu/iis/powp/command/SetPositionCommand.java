package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Implementation of IPlotterCommand for setPosition command functionality.
 */
public class SetPositionCommand extends SimpleCommand {

	public SetPositionCommand(int posX, int posY) {
		super(new Coordinates(posX, posY));
	}
	
	public SetPositionCommand(Coordinates coordinates) {
		super(coordinates);
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.setPosition(super.getCoordinates().getPosX(), super.getCoordinates().getPosY());
	}

}
