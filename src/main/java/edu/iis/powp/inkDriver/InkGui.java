package edu.iis.powp.inkDriver;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.accessibility.AccessibleRelation;
import javax.swing.*;

import edu.iis.powp.app.gui.WindowComponent;

public class InkGui extends JFrame implements WindowComponent, IGui{

    private JTextField inkAmount;
    private JProgressBar progressBar;

    public InkGui(IGuiLogic guiLogic) {

        guiLogic.setGui(this);
        this.setTitle("Ink controller");
        this.setSize(400, 200);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JCheckBox inkControlCheckBox = new JCheckBox("Use Ink Controller", false);
        inkControlCheckBox.addActionListener((ActionEvent e) -> guiLogic.useInkController(e));
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(inkControlCheckBox,c);

        JCheckBox criticalChargeCheckBox = new JCheckBox("Use Critical Charge", false);
        criticalChargeCheckBox.addActionListener((ActionEvent e) -> guiLogic.useCriticalCharge(e));
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(criticalChargeCheckBox,c);

        JButton btnIncreaseMaxInkValue = new JButton("Add 100 to ink level");
        btnIncreaseMaxInkValue.addActionListener((ActionEvent e) -> guiLogic.changeInkLevel(100));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnIncreaseMaxInkValue, c);

        JButton btnDecreaseMaxInkValue = new JButton("Subtract 100 from ink level");
        btnDecreaseMaxInkValue.addActionListener((ActionEvent e) -> guiLogic.changeInkLevel(-100));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnDecreaseMaxInkValue, c);

        inkAmount = new JTextField("");
        inkAmount.setEditable(false);
        inkAmount.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(inkAmount, c);

        progressBar = new JProgressBar(0, 1000);
        progressBar.setStringPainted(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(progressBar,c);

        JButton btnClearCommand = new JButton("Refill");
        btnClearCommand.addActionListener((ActionEvent e) -> guiLogic.fillInk());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    @Override
    public void informationPopUp(){
        JOptionPane.showMessageDialog(this, "Low Ink Level!", "Ink Level Warning", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void updateValueInGui(float value, float maxInkLevel) {
        inkAmount.setText("Ink value:" + String.valueOf(value) + " out of " + String.valueOf(maxInkLevel));
        int pb = (int) ((value/maxInkLevel)*1000);
        progressBar.setValue(pb);
    }
}
