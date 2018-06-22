package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyRotationCommandListener;
import edu.iis.powp.commandtransformer.model.RotationCommandComponentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RotationCommandComponent extends CommandTransformerCreatorComponent<ApplyRotationCommandListener> {
    private JSpinner rotationSpinner;

    public RotationCommandComponent(ApplyRotationCommandListener applyButtonClickListener) {
        super(applyButtonClickListener);
    }

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 2;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);
        RotationCommandComponentModel model = applyButtonClickListener.getDataModel();

        JLabel rotationLabel = new JLabel("Rotation:");
        rotationSpinner = new JSpinner(new SpinnerNumberModel(0, -360, 360, 1));
        rotationSpinner.addChangeListener(e -> model.setRotation((Integer) rotationSpinner.getValue()));
        constraints.gridx = 0;
        panel.add(rotationLabel, constraints);
        constraints.gridy = 1;
        panel.add(rotationSpinner, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply rotation command");
        return applyButton;
    }

    @Override
    protected void setApplyButtonActions(ActionEvent e) {
        super.setApplyButtonActions(e);
        rotationSpinner.setValue(0);
    }
}
