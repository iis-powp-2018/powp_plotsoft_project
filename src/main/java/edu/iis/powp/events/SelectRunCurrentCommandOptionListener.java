package edu.iis.powp.events;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRunCurrentCommandOptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectRunCurrentCommandOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		IPlotterCommand command = CommandsFeature.getPlotterCommandManager().getCurrentCommand();
		command.execute(driverManager.getCurrentPlotter());
	}
}