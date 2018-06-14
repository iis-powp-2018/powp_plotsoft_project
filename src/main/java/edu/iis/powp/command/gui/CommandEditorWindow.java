package edu.iis.powp.command.gui;

import edu.iis.powp.app.gui.WindowComponent;

import javax.swing.*;

public class CommandEditorWindow extends JFrame implements WindowComponent{

    private JPanel mainPanel;
    private JList list1;
    private JList list2;
    private JButton addButton;
    private JButton removeButton;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;

    public CommandEditorWindow(){
        this.setSize(600, 400);
        setContentPane(mainPanel);
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
