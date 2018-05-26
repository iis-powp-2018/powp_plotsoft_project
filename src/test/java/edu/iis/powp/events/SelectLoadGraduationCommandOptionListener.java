package edu.iis.powp.events;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.GraduationCommandDecorator;
import edu.iis.powp.decorator.MoveCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadGraduationCommandOptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if(currentCommand != null) {
            GraduationCommandDecorator decorator = new GraduationCommandDecorator(currentCommand, 5);
            manager.setCurrentCommand(decorator);
        }
    }
}
