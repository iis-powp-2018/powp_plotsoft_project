package edu.iis.powp.adapter;

import edu.iis.powp.app.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;

public class InkGui extends JFrame implements WindowComponent{

    private static InkGui ourInstance = new InkGui();

    public static InkGui getInstance() {
        return ourInstance;
    }

    private JTextArea inkAmount;

    private InkGui() {
        this.setTitle("Ink controller");
        this.setSize(400, 200);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        inkAmount = new JTextArea("");
        inkAmount.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(inkAmount, c);

        JButton btnClearCommand = new JButton("Fill up");
        // btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
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

    public void updateValue(float dane){
        inkAmount.setText("Ink value:" + String.valueOf(dane));
    }
}
