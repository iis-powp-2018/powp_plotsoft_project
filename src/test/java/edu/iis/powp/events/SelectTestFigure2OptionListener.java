package edu.iis.powp.events;

import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.manager.PlotterControlsManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestFigure2OptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectTestFigure2OptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FiguresJoe.figureScript2(driverManager.getCurrentPlotter());
		PlotterControlsManager.commandList.add("figureScript2");
	}
}