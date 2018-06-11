package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyStretchingCommandListener;
import edu.iis.powp.commandtransformer.model.StretchingCommandComponentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StretchingCommandComponent extends CommandTransformerCreatorComponent<ApplyStretchingCommandListener> {
    private JCheckBox stretchXCheckBox;
    private JCheckBox stretchYCheckBox;
    private JSpinner stretchingSpinner;

    public StretchingCommandComponent(ApplyStretchingCommandListener applyButtonClickListener) {
        super(applyButtonClickListener);
    }

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 3;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);
        StretchingCommandComponentModel model = applyButtonClickListener.getDataModel();

        stretchXCheckBox = new JCheckBox("Stretch X");
        stretchXCheckBox.addChangeListener(e -> model.setStretchX(stretchXCheckBox.isSelected()));
        constraints.gridx = 0;
        panel.add(stretchXCheckBox, constraints);

        stretchYCheckBox = new JCheckBox("Stretch Y");
        stretchYCheckBox.addChangeListener(e -> model.setStretchY(stretchYCheckBox.isSelected()));
        constraints.gridx = 1;
        panel.add(stretchYCheckBox, constraints);
        constraints.gridy = 1;

        JLabel stretchingLabel = new JLabel("Stretching:");
        stretchingSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        stretchingSpinner.addChangeListener(e -> model.setStretchValue((Integer) stretchingSpinner.getValue()));

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
        return applyButton;
    }

    @Override
    protected void setApplyButtonActions(ActionEvent e) {
        super.setApplyButtonActions(e);
        stretchingSpinner.setValue(0);
        stretchXCheckBox.setSelected(false);
        stretchYCheckBox.setSelected(false);
    }
}
