package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyGraduationCommandListener;
import edu.iis.powp.commandtransformer.model.GraduationCommandComponentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GraduationCommandComponent extends CommandTransformerCreatorComponent<ApplyGraduationCommandListener> {
    private JSpinner graduationSpinner;

    public GraduationCommandComponent(ApplyGraduationCommandListener applyButtonClickListener) {
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
        GraduationCommandComponentModel model = applyButtonClickListener.getDataModel();

        JLabel graduationLabel = new JLabel("Graduation:");
        graduationSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        graduationSpinner.addChangeListener(e -> model.setGraduation((Integer) graduationSpinner.getValue()));
        constraints.gridx = 0;
        panel.add(graduationLabel, constraints);
        constraints.gridy = 1;
        panel.add(graduationSpinner, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply graduation command");
        return applyButton;
    }

    @Override
    protected void setApplyButtonActions(ActionEvent e) {
        super.setApplyButtonActions(e);
        graduationSpinner.setValue(0);
    }
}
