package edu.iis.powp.command.gui;

import edu.iis.powp.app.gui.WindowComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CommandEditorWindow extends JFrame implements WindowComponent{

    private JPanel mainPanel;
    private JList basicCommandsList;
    private JList newCommandList;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton clearButton;
    private JTextField nameTextField;
    private JTextField argumentsTextField;
    DefaultListModel<String> basicCommands;
    DefaultListModel<String> newCommands;
    ArrayList<String> arguments;

    public CommandEditorWindow(){
        setTitle("Command Editor");
        setSize(600, 400);
        setContentPane(mainPanel);

        arguments = new ArrayList<>();
        basicCommands = new DefaultListModel<>();
        basicCommandsList.setModel(basicCommands);

        for(int i = 0; i < 30; i++){
            basicCommands.addElement(String.valueOf(i));
        }

        newCommands = new DefaultListModel<>();
        newCommandList.setModel(newCommands);

        basicCommandsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    newCommands.addElement(basicCommands.get(basicCommandsList.getSelectedIndex()));
                    arguments.add("");
                }
            }
        });

        addButton.addActionListener(e -> {
            newCommands.addElement(basicCommands.get(basicCommandsList.getSelectedIndex()));
            arguments.add("");
        });

        newCommandList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                argumentsTextField.setText(arguments.get(newCommandList.getSelectedIndex()));
            }
        });

        argumentsTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void saveArgument(){
                if(newCommandList.getSelectedIndex() != -1) {
                    arguments.set(newCommandList.getSelectedIndex(), argumentsTextField.getText());
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                saveArgument();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveArgument();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveArgument();
            }
        });

        removeButton.addActionListener(e -> {
            argumentsTextField.setText("");
            arguments.remove(newCommandList.getSelectedIndex());
            newCommands.remove(newCommandList.getSelectedIndex());
        });

        clearButton.addActionListener(e -> {
            newCommands.clear();
            arguments.clear();
        });

        saveButton.addActionListener(e -> {
            saveCommands();
        });
    }

    private boolean saveCommands(){
        // newCommands - nazwy nowych komend
        // arguments - lista argumentów do komend

        return false;
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
