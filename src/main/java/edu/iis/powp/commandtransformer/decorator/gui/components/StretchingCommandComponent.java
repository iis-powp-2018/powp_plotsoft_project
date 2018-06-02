package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyStretchingCommandListener;

import javax.swing.*;
import java.awt.*;

public class StretchingCommandComponent extends CommandTransformerCreatorComponent {

    private JCheckBox stretchXCheckBox;
    private JCheckBox stretchYCheckBox;
    private JSpinner stretchingSpinner;

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 3;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);

        stretchXCheckBox = new JCheckBox("Stretch X");
        constraints.gridx = 0;
        panel.add(stretchXCheckBox, constraints);

        stretchYCheckBox = new JCheckBox("Stretch Y");
        constraints.gridx = 1;
        panel.add(stretchYCheckBox, constraints);
        constraints.gridy = 1;

        JLabel stretchingLabel = new JLabel("Stretching:");
        stretchingSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(stretchingLabel, constraints);
        constraints.gridy = 2;
        constraints.weightx = 0;
        constraints.gridwidth = 2;
        panel.add(stretchingSpinner, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply stretching command");
        setApplyButtonActionListeners(applyButton);
        return applyButton;
    }

    @Override
    protected void setApplyButtonActionListeners(JButton applyButton) {
        applyButton.addActionListener(new ApplyStretchingCommandListener(stretchingSpinner, stretchXCheckBox, stretchYCheckBox));

    }
}
