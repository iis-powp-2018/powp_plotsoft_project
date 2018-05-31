package edu.iis.powp.decorator.gui.components;

import javax.swing.*;
import java.awt.*;

public abstract class CommandTransformerCreatorComponent {
    public final JPanel buildComponent() {
        JPanel panel = constructJPanel();
        addContent(panel, constructElementGridBagConstraints());
        addApplyButton(panel, constructApplyButtonGridBagConstraints());
        return panel;
    }

    protected JPanel constructJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    protected GridBagConstraints constructElementGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        return constraints;
    }

    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.0;
        constraints.ipady = 20;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 0);
        return constraints;
    }

    protected void addContent(JPanel panel, GridBagConstraints constraints) {

    }

    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = new JButton("Apply command");
        panel.add(applyButton, constraints);
        return applyButton;
    }
}
