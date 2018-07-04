package edu.iis.powp.events;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.manager.PlotterControlsManager;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLoadSecretCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		List<IPlotterCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(-20, -50));
		commands.add(new DrawToCommand(-20, -50));
		commands.add(new SetPositionCommand(-20, -40));
		commands.add(new DrawToCommand(-20, 50));
		commands.add(new SetPositionCommand(0, -50));
		commands.add(new DrawToCommand(0, -50));
		commands.add(new SetPositionCommand(0, -40));
		commands.add(new DrawToCommand(0, 50));
		commands.add(new SetPositionCommand(70, -50));
		commands.add(new DrawToCommand(20, -50));
		commands.add(new DrawToCommand(20, 0));
		commands.add(new DrawToCommand(70, 0));
		commands.add(new DrawToCommand(70, 50));
		commands.add(new DrawToCommand(20, 50));
		
		PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
		manager.setCurrentCommand(commands, "TopSecretCommand");

		PlotterControlsManager.commandList.add("secretCommand");
	}
}