package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorWindow extends JFrame implements WindowComponent {

    private PlotterCommandManager commandManager;

    private JMenuBar menuBar = null;
    private JMenu addMenu = null;

    private JTextArea currentCommandField;

    private String observerListString;
    private JTextArea observerListField;


    public CommandEditorWindow(PlotterCommandManager commandManager){

        this.setTitle("Command Editor");
        this.setSize(400, 400);

        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);
        this.addMenu = new JMenu("Add");
        this.menuBar.add(this.addMenu);


        JMenuItem draw = new JMenuItem("Draw");
        this.addMenu.add(draw);

        JMenuItem setPosition = new JMenuItem("Set Position");
        this.addMenu.add(setPosition);



        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);


        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);

        JButton btnClearCommand = new JButton("Delete last command");
        //btnClearCommand.addActionListener((ActionEvent e) -> this.OpenDrawWindow());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Create command");
        //btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);


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

