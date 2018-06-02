package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyMoveCommandListener;

import javax.swing.*;
import java.awt.*;

public class MoveCommandComponent extends CommandTransformerCreatorComponent {
    private JSpinner moveXSpinner;
    private JSpinner moveYSpinner;

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 2;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);

        JLabel moveXLabel = new JLabel("X movement:");
        moveXSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        constraints.gridx = 0;
        panel.add(moveXLabel, constraints);
        constraints.gridy = 1;
        panel.add(moveXSpinner, constraints);

        JLabel moveYLabel = new JLabel("Y movement:");
        moveYSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
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
        setApplyButtonActionListeners(applyButton);
        return applyButton;
    }

    @Override
    protected void setApplyButtonActionListeners(JButton applyButton) {
        applyButton.addActionListener(new ApplyMoveCommandListener(moveXSpinner, moveYSpinner));
    }
}
