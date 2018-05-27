package edu.iis.powp.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.GraduationCommandDecorator;
import edu.iis.powp.decorator.StretchingCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadStretchingCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if(currentCommand != null) {
            StretchingCommandDecorator decorator = new StretchingCommandDecorator(currentCommand, true, false, 2);
            manager.setCurrentCommand(decorator);
        }
    }
}
