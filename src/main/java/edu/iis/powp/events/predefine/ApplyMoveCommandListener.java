package edu.iis.powp.events.predefine;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.MoveCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyMoveCommandListener implements ActionListener {

    private JSpinner moveYSpinner;
    private JSpinner moveXSpinner;

    public ApplyMoveCommandListener(JSpinner moveXSpinner, JSpinner moveYSpinner) {
        this.moveYSpinner = moveYSpinner;
        this.moveXSpinner = moveXSpinner;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        int moveX;
        int moveY;
        try {
            moveX = (Integer) moveXSpinner.getValue();
            moveY = (Integer) moveYSpinner.getValue();
        } catch (ClassCastException f) {
            return; // TODO: Implement error logging
        }
        if (currentCommand != null) {
            MoveCommandDecorator newCommand = new MoveCommandDecorator(currentCommand, moveX, moveY);
            commandManager.setCurrentCommand(newCommand);
            moveXSpinner.setValue(0);
            moveYSpinner.setValue(0);
        }

    }
}