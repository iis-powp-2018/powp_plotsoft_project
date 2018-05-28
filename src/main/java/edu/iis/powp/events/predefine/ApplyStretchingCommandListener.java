package edu.iis.powp.events.predefine;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.StretchingCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyStretchingCommandListener implements ActionListener {
    private JSpinner stretchingSpinner;
    private JCheckBox stretchXCheckBox;
    private JCheckBox stretchYCheckBox;

    public ApplyStretchingCommandListener(JSpinner stretchingSpinner, JCheckBox stretchXCheckBox, JCheckBox stretchYCheckBox) {
        this.stretchingSpinner = stretchingSpinner;
        this.stretchXCheckBox = stretchXCheckBox;
        this.stretchYCheckBox = stretchYCheckBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        int stretching;
        try {
            stretching = (Integer) stretchingSpinner.getValue();
        } catch (ClassCastException f) {
            return; // TODO: Implement error logging
        }
        if (currentCommand != null) {
            StretchingCommandDecorator newCommand = new StretchingCommandDecorator(currentCommand, stretchXCheckBox.isSelected(), stretchYCheckBox.isSelected(), stretching);
            commandManager.setCurrentCommand(newCommand);
            stretchXCheckBox.setSelected(false);
            stretchYCheckBox.setSelected(false);
            stretchingSpinner.setValue(0);
        }
    }
}