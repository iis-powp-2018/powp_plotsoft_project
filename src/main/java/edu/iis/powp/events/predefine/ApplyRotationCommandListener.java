package edu.iis.powp.events.predefine;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.RotationCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyRotationCommandListener implements ActionListener {
    private JSpinner rotationSpinner;

    public ApplyRotationCommandListener(JSpinner rotationSpinner) {
        this.rotationSpinner = rotationSpinner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        int rotation;
        try {
            rotation = (Integer) rotationSpinner.getValue();
        } catch (ClassCastException f) {
            return; // TODO: Implement error logging
        }
        if (currentCommand != null) {
            RotationCommandDecorator newCommand = new RotationCommandDecorator(currentCommand, rotation);
            commandManager.setCurrentCommand(newCommand);
            rotationSpinner.setValue(0);
        }
    }
}