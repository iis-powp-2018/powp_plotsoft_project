package edu.iis.powp.decorator.gui.events;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunCommandListener implements ActionListener {
    private DriverManager driverManager;

    public RunCommandListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        if (currentCommand != null) {
            currentCommand.execute(driverManager.getCurrentPlotter());
        }
    }
}