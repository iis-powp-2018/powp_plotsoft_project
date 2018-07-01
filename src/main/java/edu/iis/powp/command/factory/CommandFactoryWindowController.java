package edu.iis.powp.command.factory;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.List;

class CommandFactoryWindowController implements Serializable {

    private final CommandFactoryWindow commandFactoryWindow;

    CommandFactoryWindowController(CommandFactoryWindow commandFactoryWindow) {
        this.commandFactoryWindow = commandFactoryWindow;
    }

    void handleClearButton(DefaultListModel<IPlotterCommand> jListModel) {

        jListModel.clear();
        commandFactoryWindow.getDrawPanelController().clearPanel();
    }

    void handleSaveButton(DefaultListModel<IPlotterCommand> jListModel, JComboBox complexComboBox,
            JTextField commandNameField) {

        String commandName = commandNameField.getText();
        CommandFactory commandFactory = CommandFactory.create(commandName);
        Enumeration<IPlotterCommand> elements = jListModel.elements();
        while (elements.hasMoreElements()) {
            IPlotterCommand command = elements.nextElement();
            commandFactory.addCommand(command);
        }
        ICompoundCommand newCommand = commandFactory.build();
        commandFactoryWindow.getCommandRegistry().registerComplexCommand(newCommand);
        complexComboBox.addItem(newCommand);
        commandNameField.setText("");
        jListModel.clear();
        commandFactoryWindow.getDrawPanelController().clearPanel();

    }

    void handleComplexAddButton(DefaultListModel<IPlotterCommand> jListModel, JComboBox complexComboBox) {
        ICompoundCommand selectedItem = (ICompoundCommand) complexComboBox.getSelectedItem();
        jListModel.addElement(selectedItem);
        selectedItem.execute(commandFactoryWindow.getLineAdapterPlotterDriver());
    }

    void handleBasicAddButton(DefaultListModel<IPlotterCommand> jListModel,
            JComboBox<CommandFactoryWindow.ConstructorDecorator> basicComboBox) {
        new DynamicParameterInputWindow(commandFactoryWindow, jListModel, basicComboBox);
    }

}
