package edu.iis.powp.adapter;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.iis.powp.app.gui.WindowComponent;


public class InkGui extends JFrame implements WindowComponent {

    private JTextArea inkAmount;

    public InkGui() {
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
}

