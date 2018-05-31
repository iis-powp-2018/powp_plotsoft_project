package edu.iis.powp.decorator.gui.components;

import edu.iis.powp.decorator.gui.events.ApplyFlipCommandListener;

import javax.swing.*;
import java.awt.*;

public class FlipCommandComponent extends CommandTransformerCreatorComponent {
    private JCheckBox flipXCheckBox;
    private JCheckBox flipYCheckBox;

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        flipXCheckBox = new JCheckBox("Flip X");
        constraints.gridx = 0;
        panel.add(flipXCheckBox, constraints);


        flipYCheckBox = new JCheckBox("Flip Y");
        constraints.gridx = 1;
        panel.add(flipYCheckBox, constraints);
    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply flip command");
        applyButton.addActionListener(new ApplyFlipCommandListener(flipYCheckBox, flipXCheckBox));
        return applyButton;
    }
}