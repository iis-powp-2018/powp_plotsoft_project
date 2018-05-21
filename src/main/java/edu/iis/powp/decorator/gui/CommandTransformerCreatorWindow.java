package edu.iis.powp.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.FlipCommandDecorator;
import edu.iis.powp.decorator.MoveCommandDecorator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CommandTransformerCreatorWindow extends JFrame implements WindowComponent {
    private PlotterCommandManager commandManager;
    private DriverManager driverManager;

    public CommandTransformerCreatorWindow(DriverManager driverManager, PlotterCommandManager plotterCommandManager) {
        this.commandManager = plotterCommandManager;
        this.driverManager = driverManager;

        this.setTitle("Command Transformer Creator");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 0;

        List<JPanel> panelList = new LinkedList<>();
        panelList.add(buildFlipCommandPanel());
        panelList.add(buildMoveCommandPanel());
        panelList.add(buildRunCommandPanel());

        panelList.forEach(panel -> {
            content.add(panel, constraints);
            constraints.gridy++;
        });
    }

    private JPanel buildFlipCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;

        JCheckBox flipXCheckBox = new JCheckBox("Flip X");
        constraints.gridx = 0;
        panel.add(flipXCheckBox, constraints);


        JCheckBox flipYCheckBox = new JCheckBox("Flip Y");
        constraints.gridx = 1;
        panel.add(flipYCheckBox, constraints);

        JButton applyButton = new JButton("Apply flip command");
        constraints.ipady = 20;
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 0);

        applyButton.addActionListener(event -> {
            IPlotterCommand currentCommand = commandManager.getCurrentCommand();
            if (currentCommand != null) {
                FlipCommandDecorator newCommand = new FlipCommandDecorator(currentCommand, flipXCheckBox.isSelected(), flipYCheckBox.isSelected());
                commandManager.setCurrentCommand(newCommand);
                flipXCheckBox.setSelected(false);
                flipYCheckBox.setSelected(false);
            }
        });

        panel.add(applyButton, constraints);

        return panel;
    }

    private JPanel buildMoveCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;


        JLabel moveXLabel = new JLabel("X movement:");
        JSpinner moveXSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));

        constraints.gridx = 0;
        panel.add(moveXLabel, constraints);
        constraints.gridy = 1;
        panel.add(moveXSpinner, constraints);


        JLabel moveYLabel = new JLabel("Y movement:");
        JSpinner moveYSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(moveYLabel, constraints);
        constraints.gridy = 1;
        panel.add(moveYSpinner, constraints);

        JButton applyButton = new JButton("Apply move command");
        constraints.ipady = 20;
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 0);

        applyButton.addActionListener(event -> {
            IPlotterCommand currentCommand = commandManager.getCurrentCommand();
            int moveX;
            int moveY;
            try {
                moveX = (Integer) moveXSpinner.getValue();
                moveY = (Integer) moveYSpinner.getValue();
            } catch (ClassCastException e) {
                return; // TODO: Implement error logging
            }
            if (currentCommand != null) {
                MoveCommandDecorator newCommand = new MoveCommandDecorator(currentCommand, moveX, moveY);
                commandManager.setCurrentCommand(newCommand);
                moveXSpinner.setValue(0);
                moveYSpinner.setValue(0);
            }
        });

        panel.add(applyButton, constraints);

        return panel;
    }

    private JPanel buildRunCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JButton runCommandButton = new JButton("Run command");
        constraints.fill = GridBagConstraints.HORIZONTAL | GridBagConstraints.VERTICAL;
        constraints.ipady = 20;
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 10, 0);

        runCommandButton.addActionListener(event -> {
            IPlotterCommand currentCommand = commandManager.getCurrentCommand();
            if (currentCommand != null) {
                currentCommand.execute(driverManager.getCurrentPlotter());
            }
        });

        panel.add(runCommandButton, constraints);

        return panel;
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
