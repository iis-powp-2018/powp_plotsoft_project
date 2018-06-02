package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyRotationCommandListener;

import javax.swing.*;
import java.awt.*;

public class RotationCommandComponent extends CommandTransformerCreatorComponent {

    private JSpinner rotationSpinner;

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 2;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);

        JLabel rotationLabel = new JLabel("Rotation:");
        rotationSpinner = new JSpinner(new SpinnerNumberModel(0, -360, 360, 1));
        constraints.gridx = 0;
        panel.add(rotationLabel, constraints);
        constraints.gridy = 1;
        panel.add(rotationSpinner, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply rotation command");
        setApplyButtonActionListeners(applyButton);
        return applyButton;
    }

    @Override
    protected void setApplyButtonActionListeners(JButton applyButton) {
        applyButton.addActionListener(new ApplyRotationCommandListener(rotationSpinner));

    }
}
