package edu.iis.powp.events.predefine;

import edu.iis.powp.command.manager.PlotterControlsManager;
import edu.iis.powp.features.DrawerFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectClearPanelOptionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		DrawerFeature.getDrawerController().clearPanel();
		PlotterControlsManager.commandList.clear();
	}
}
