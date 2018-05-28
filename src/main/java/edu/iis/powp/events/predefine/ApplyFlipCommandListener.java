package edu.iis.powp.events.predefine;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.FlipCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyFlipCommandListener implements ActionListener {

    private JCheckBox flipYCheckBox;
    private JCheckBox flipXCheckBox;

    public ApplyFlipCommandListener(JCheckBox flipYCheckBox, JCheckBox flipXCheckBox) {
        this.flipYCheckBox = flipYCheckBox;
        this.flipXCheckBox = flipXCheckBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if (currentCommand != null) {
            FlipCommandDecorator newCommand = new FlipCommandDecorator(currentCommand, flipXCheckBox.isSelected(), flipYCheckBox.isSelected());
            manager.setCurrentCommand(newCommand);
            flipXCheckBox.setSelected(false);
            flipYCheckBox.setSelected(false);
        }
    }
}
