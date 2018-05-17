package edu.iis.powp.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.FlipCommandDecorator;
import edu.iis.powp.decorator.MoveCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadFlipCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if(currentCommand != null) {
            FlipCommandDecorator decorator = new FlipCommandDecorator(currentCommand, true, true);
            manager.setCurrentCommand(decorator);
        }
    }
}
