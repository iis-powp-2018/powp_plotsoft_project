package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.controllers.ink.InkControllerInterface;
import edu.iis.powp.controllers.ink.SimmulationInkController;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.ILine;

/**
 * Line adapter - IPlotter with DrawPanelController object.
 */
public class LineAdapterPlotterDriver implements IPlotter {
	private ILine line;
	private int startX = 0, startY = 0;
	private String name;

	private DrawPanelController drawController;
	private InkControllerInterface inkController;

	public LineAdapterPlotterDriver(DrawPanelController drawController, ILine line, String name) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
		this.inkController = SimmulationInkController.getInstance();
	}

	@Override
	public void setPosition(int x, int y) {
		this.startX = x;
		this.startY = y;
	}

	@Override
	public void drawTo(int x, int y) {
		float lineLength = calculateLineLength(this.startX, this.startY, x, y);
		boolean enoughInk = inkController.checkInkIsEnough(lineLength);

		if(enoughInk) {
			line.setStartCoordinates(this.startX, this.startY);
			this.setPosition(x, y);
			line.setEndCoordinates(x, y);
			inkController.reduceInkLevel(lineLength);
			drawController.drawLine(line);
		}
		else {
			//TODO do not draw + log? + back to fill
		}
	}

	private float calculateLineLength(int startX, int startY, int x, int y) {
		return (float) Math.sqrt((startX - x) * (startX - x) + (startY - y) * (startY - y));
	}

	@Override
	public String toString() {
		return "Plotter Simulator - " + name;
	}
}
