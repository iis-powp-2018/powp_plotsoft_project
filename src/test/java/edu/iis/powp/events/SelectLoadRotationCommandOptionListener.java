package edu.iis.powp.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.RotationCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadRotationCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if (currentCommand != null) {
            RotationCommandDecorator decorator = new RotationCommandDecorator(currentCommand, 90);
            manager.setCurrentCommand(decorator);
        }
    }
}
