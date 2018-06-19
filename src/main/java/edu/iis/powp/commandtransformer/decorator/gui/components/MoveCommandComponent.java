package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyMoveCommandListener;
import edu.iis.powp.commandtransformer.model.MoveCommandComponentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MoveCommandComponent extends CommandTransformerCreatorComponent<ApplyMoveCommandListener> {
    private JSpinner moveXSpinner;
    private JSpinner moveYSpinner;

    public MoveCommandComponent(ApplyMoveCommandListener applyButtonClickListener) {
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
        MoveCommandComponentModel model = applyButtonClickListener.getDataModel();

        JLabel moveXLabel = new JLabel("X movement:");
        moveXSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        moveXSpinner.addChangeListener(e -> model.setMovementX((Integer) moveXSpinner.getValue()));
        constraints.gridx = 0;
        panel.add(moveXLabel, constraints);
        constraints.gridy = 1;
        panel.add(moveXSpinner, constraints);

        JLabel moveYLabel = new JLabel("Y movement:");
        moveYSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        moveYSpinner.addChangeListener(e -> model.setMovementY((Integer) moveYSpinner.getValue()));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(moveYLabel, constraints);
        constraints.gridy = 1;
        panel.add(moveYSpinner, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply move command");
        return applyButton;
    }

    @Override
    protected void setApplyButtonActions(ActionEvent e) {
        super.setApplyButtonActions(e);
        moveXSpinner.setValue(0);
        moveYSpinner.setValue(0);
    }
}
