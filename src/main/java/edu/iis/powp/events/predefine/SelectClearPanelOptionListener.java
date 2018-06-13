package edu.iis.powp.events.predefine;

import edu.iis.powp.features.CommandsFeature;
import edu.iis.powp.features.DrawerFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectClearPanelOptionListener implements ActionListener {
	CommandsFeature commandsFeature = new CommandsFeature();
	@Override
	public void actionPerformed(ActionEvent e) {
		DrawerFeature.getDrawerController().clearPanel();
		commandsFeature.commandList.clear();
	}
}
