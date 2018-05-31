package edu.iis.powp.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.decorator.gui.components.CommandTransformerCreatorComponent;
import edu.iis.powp.decorator.gui.events.RunCommandListener;

import javax.swing.*;
import java.awt.*;

public class CommandTransformerCreatorWindow extends JFrame implements WindowComponent {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 600;
    private int gridY;
    private final JPanel content;

    public CommandTransformerCreatorWindow(DriverManager driverManager) {
        this.content = new JPanel();
        this.content.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setWheelScrollingEnabled(true);

        this.setTitle("Command Transformer Creator");
        this.setContentPane(scrollPane);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.gridY = 0;

        addComponent("Command runner", new RunCommandComponent(driverManager));
    }

    public void addComponent(String title, CommandTransformerCreatorComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = gridY;

        JPanel builtComponent = component.buildComponent();
        builtComponent.setBorder(BorderFactory.createTitledBorder(title));
        content.add(builtComponent, constraints);
        gridY++;
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    private class RunCommandComponent extends CommandTransformerCreatorComponent {

        private DriverManager driverManager;

        RunCommandComponent(DriverManager driverManager) {
            this.driverManager = driverManager;
        }

        @Override
        protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
            JButton runCommandButton = super.addApplyButton(panel, constraints);
            runCommandButton.setText("Run command");
            runCommandButton.addActionListener(new RunCommandListener(driverManager));
            return runCommandButton;
        }
    }
}
