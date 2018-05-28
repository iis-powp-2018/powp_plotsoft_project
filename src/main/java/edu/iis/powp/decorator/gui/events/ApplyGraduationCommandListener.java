package edu.iis.powp.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.GraduationCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyGraduationCommandListener implements ActionListener {
    private JSpinner graduationSpinner;

    public ApplyGraduationCommandListener(JSpinner graduationSpinner) {
        this.graduationSpinner = graduationSpinner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        int graduation;
        try {
            graduation = (Integer) graduationSpinner.getValue();
        } catch (ClassCastException f) {
            return; // TODO: Implement error logging
        }
        if (currentCommand != null) {
            GraduationCommandDecorator newCommand = new GraduationCommandDecorator(currentCommand, graduation);
            commandManager.setCurrentCommand(newCommand);
            graduationSpinner.setValue(0);
        }
    }
}