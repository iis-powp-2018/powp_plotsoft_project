package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyFlipCommandListener;
import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyGraduationCommandListener;

import javax.swing.*;
import java.awt.*;

public class GraduationCommandComponent extends CommandTransformerCreatorComponent {

    private JSpinner graduationSpinner;

    @Override
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = super.constructApplyButtonGridBagConstraints();
        constraints.gridy = 2;
        return constraints;
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);

        JLabel graduationLabel = new JLabel("Graduation:");
        graduationSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
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
    protected void setApplyButtonActionListeners(JButton applyButton) {
        applyButton.addActionListener(new ApplyGraduationCommandListener(graduationSpinner));
    }
}
