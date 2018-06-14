package edu.iis.powp.command.gui;

import edu.iis.powp.app.gui.WindowComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandEditorWindow extends JFrame implements WindowComponent{

    private JPanel mainPanel;
    private JList basicCommandsList;
    private JList newCommandList;
    private JButton addButton;
    private JButton removeButton;
    private JTextField textField1;
    private JButton saveButton;
    private JButton clearButton;
    DefaultListModel<String> basicCommands;
    DefaultListModel<String> newCommands;


    public CommandEditorWindow(){
        setSize(600, 400);
        setContentPane(mainPanel);
        basicCommands = new DefaultListModel<>();
        basicCommandsList.setModel(basicCommands);

        for(int i = 0; i < 40; i++){
            basicCommands.addElement(String.valueOf(i));
        }

        newCommands = new DefaultListModel<>();
        newCommandList.setModel(newCommands);


        addButton.addActionListener(e -> {
            newCommands.addElement(basicCommands.get(basicCommandsList.getSelectedIndex()));
        });

        removeButton.addActionListener(e -> {
            newCommands.remove(newCommandList.getSelectedIndex());
        });

        clearButton.addActionListener(e -> {
            newCommands.clear();
        });
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
