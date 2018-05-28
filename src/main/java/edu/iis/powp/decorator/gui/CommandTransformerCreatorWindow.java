package edu.iis.powp.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.gui.events.*;
import edu.iis.powp.events.predefine.*;

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
        this.setSize(500, 630);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;

        List<JPanel> panelList = new LinkedList<>();
        panelList.add(buildFlipCommandPanel());
        panelList.add(buildMoveCommandPanel());
        panelList.add(buildGraduationCommandPanel());
        panelList.add(buildStretchingCommandPanel());
        panelList.add(buildRotationCommandPanel());
        panelList.add(buildRunCommandPanel());

        panelList.forEach(panel -> {
            content.add(panel, constraints);
            constraints.gridy++;
        });
    }

    private JPanel buildFlipCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();

        JCheckBox flipXCheckBox = new JCheckBox("Flip X");
        constraints.gridx = 0;
        panel.add(flipXCheckBox, constraints);


        JCheckBox flipYCheckBox = new JCheckBox("Flip Y");
        constraints.gridx = 1;
        panel.add(flipYCheckBox, constraints);

        JButton applyButton = new JButton("Apply flip command");
        setPaddings(constraints);

        applyButton.addActionListener(new ApplyFlipCommandListener(flipYCheckBox,flipXCheckBox));

        panel.add(applyButton, constraints);

        return panel;
    }

    private JPanel buildMoveCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();


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
        setPaddings(constraints);

        applyButton.addActionListener(new ApplyMoveCommandListener(moveXSpinner,moveYSpinner));

        panel.add(applyButton, constraints);

        return panel;
    }

    private JPanel buildGraduationCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();

        JLabel graduationLabel = new JLabel("Graduation:");
        JSpinner graduationSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));

        constraints.gridx = 0;
        panel.add(graduationLabel, constraints);
        constraints.gridy = 1;
        panel.add(graduationSpinner, constraints);

        JButton applyButton = new JButton("Apply graduation command");
        setPaddings(constraints);

        applyButton.addActionListener(new ApplyGraduationCommandListener(graduationSpinner));

        panel.add(applyButton, constraints);
        return panel;
    }

    private JPanel buildStretchingCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();

        JCheckBox stretchXCheckBox = new JCheckBox("Stretch X");
        constraints.gridx = 0;
        panel.add(stretchXCheckBox, constraints);


        JCheckBox stretchYCheckBox = new JCheckBox("Stretch Y");
        constraints.gridx = 1;
        panel.add(stretchYCheckBox, constraints);
        constraints.gridy = 1;

        JLabel stretchingLabel = new JLabel("Stretching:");
        JSpinner stretchingSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(stretchingLabel, constraints);
        constraints.gridy = 2;
        panel.add(stretchingSpinner, constraints);

        JButton applyButton = new JButton("Apply stretching command");
        setPaddings(constraints);

        applyButton.addActionListener(new ApplyStretchingCommandListener(stretchingSpinner,stretchXCheckBox,stretchYCheckBox));

        constraints.gridy = 3;
        panel.add(applyButton, constraints);
        return panel;

    }

    private JPanel buildRotationCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = getGridBagConstraints();

        JLabel rotationLabel = new JLabel("Rotation:");
        JSpinner rotationSpinner = new JSpinner(new SpinnerNumberModel(0, -360, 360, 1));

        constraints.gridx = 0;
        panel.add(rotationLabel, constraints);
        constraints.gridy = 1;
        panel.add(rotationSpinner, constraints);

        JButton applyButton = new JButton("Apply rotation command");
        setPaddings(constraints);

        applyButton.addActionListener(new ApplyRotationCommandListener(rotationSpinner));

        panel.add(applyButton, constraints);
        return panel;
    }

    private JPanel buildRunCommandPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JButton runCommandButton = new JButton("Run command");
        constraints.fill = GridBagConstraints.HORIZONTAL | GridBagConstraints.VERTICAL;
        setPaddings(constraints);

        runCommandButton.addActionListener(new RunCommandListener(driverManager));

        panel.add(runCommandButton, constraints);

        return panel;
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        return constraints;
    }

    private void setPaddings(GridBagConstraints constraints) {
        constraints.ipady = 20;
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
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
