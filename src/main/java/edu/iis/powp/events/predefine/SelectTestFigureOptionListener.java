package edu.iis.powp.events.predefine;

import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestFigureOptionListener implements ActionListener {

	private DriverManager driverManager;
	CommandsFeature commandsFeature = new CommandsFeature();

	public SelectTestFigureOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FiguresJoe.figureScript1(driverManager.getCurrentPlotter());
		commandsFeature.commandList.add("figureScript1");

	}
}
