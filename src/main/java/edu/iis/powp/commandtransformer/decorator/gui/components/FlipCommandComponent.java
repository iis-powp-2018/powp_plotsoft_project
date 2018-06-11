package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyFlipCommandListener;
import edu.iis.powp.commandtransformer.model.FlipCommandComponentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FlipCommandComponent extends CommandTransformerCreatorComponent<ApplyFlipCommandListener> {
    private JCheckBox flipXCheckBox;
    private JCheckBox flipYCheckBox;

    public FlipCommandComponent(ApplyFlipCommandListener applyButtonClickListener) {
        super(applyButtonClickListener);
    }

    @Override
    protected void addContent(JPanel panel, GridBagConstraints constraints) {
        super.addContent(panel, constraints);
        FlipCommandComponentModel model = applyButtonClickListener.getDataModel();

        flipXCheckBox = new JCheckBox("Flip X");
        flipXCheckBox.addChangeListener(e -> model.setFlipX(flipXCheckBox.isSelected()));
        constraints.gridx = 0;
        panel.add(flipXCheckBox, constraints);


        flipYCheckBox = new JCheckBox("Flip Y");
        flipYCheckBox.addChangeListener(e -> model.setFlipY(flipYCheckBox.isSelected()));
        constraints.gridx = 1;
        panel.add(flipYCheckBox, constraints);

    }

    @Override
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = super.addApplyButton(panel, constraints);
        applyButton.setText("Apply flip command");
        return applyButton;
    }

    @Override
    protected void setApplyButtonActions(ActionEvent e) {
        super.setApplyButtonActions(e);
        flipXCheckBox.setSelected(false);
        flipYCheckBox.setSelected(false);
    }
}
