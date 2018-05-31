package edu.iis.powp.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.decorator.gui.components.CommandTransformerCreatorComponent;
import edu.iis.powp.decorator.gui.events.RunCommandListener;

import javax.swing.*;
import java.awt.*;

public class CommandTransformerCreatorWindow extends JFrame implements WindowComponent {
    private int gridY;

    public CommandTransformerCreatorWindow(DriverManager driverManager) {
        this.gridY = 0;
        this.setTitle("Command Transformer Creator");
        this.setSize(500, 630);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        addComponent(new RunCommandComponent(driverManager));
    }

    public void addComponent(CommandTransformerCreatorComponent component) {
        Container content = this.getContentPane();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = gridY;

        content.add(component.buildComponent(), constraints);
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
