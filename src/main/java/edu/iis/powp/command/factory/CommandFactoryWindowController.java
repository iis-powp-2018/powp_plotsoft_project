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

    ActionListener handleClearButton(DefaultListModel<IPlotterCommand> jListModel) {
        return new ActionListener() {

            @Override public void actionPerformed(final ActionEvent e) {
                jListModel.clear();
                commandFactoryWindow.getDrawPanelController().clearPanel();
            }
        };
    }

    ActionListener handleSaveButton(DefaultListModel<IPlotterCommand> jListModel, JComboBox complexComboBox,
            JTextField commandNameField) {
        return new ActionListener() {

            @Override public void actionPerformed(final ActionEvent e) {
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
        };
    }

    ActionListener handleComplexAddButton(DefaultListModel<IPlotterCommand> jListModel, JComboBox complexComboBox) {
        return new ActionListener() {

            @Override public void actionPerformed(final ActionEvent e) {
                ICompoundCommand selectedItem = (ICompoundCommand) complexComboBox.getSelectedItem();
                jListModel.addElement(selectedItem);
                selectedItem.execute(commandFactoryWindow.getLineAdapterPlotterDriver());
            }
        };
    }

    ActionListener handleBasicAddButton(DefaultListModel<IPlotterCommand> jListModel,
            JComboBox<CommandFactoryWindow.ConstructorDecorator> basicComboBox) {
        return new ActionListener() {

            @Override public void actionPerformed(final ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setBounds(100, 100, 200, 100);
                CommandFactoryWindow.ConstructorDecorator selectedItem = (CommandFactoryWindow.ConstructorDecorator) basicComboBox
                        .getSelectedItem();
                Constructor<? extends IPlotterCommand> constructor = selectedItem.getConstructor();
                frame.setLayout(new GridLayout(constructor.getParameterCount() + 1, 2));
                Map<Parameter, JTextField> textFields = new LinkedHashMap<Parameter, JTextField>();
                for (Parameter parameter : constructor.getParameters()) {
                    JLabel label = new JLabel(parameter.getName());
                    frame.add(label);
                    JTextField field = new JTextField();
                    frame.add(field);
                    textFields.put(parameter, field);
                }
                JButton button = new JButton("Add");
                button.addActionListener(new ActionListener() {

                    @Override public void actionPerformed(final ActionEvent actionEvent) {
                        List<Object> arguments = new ArrayList<Object>();
                        for (Map.Entry<Parameter, JTextField> parameterJTextFieldEntry : textFields.entrySet()) {
                            Parameter param = parameterJTextFieldEntry.getKey();
                            JTextField value = parameterJTextFieldEntry.getValue();
                            Object paramValue = convertTextFieldValueToArgument(param, value.getText());
                            arguments.add(paramValue);
                        }
                        try {
                            IPlotterCommand element = constructor.newInstance(arguments.toArray());
                            element.execute(commandFactoryWindow.getLineAdapterPlotterDriver());
                            jListModel.addElement(element);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                });
                frame.add(button);
                frame.setVisible(true);
            }
        };
    }

    private Object convertTextFieldValueToArgument(final Parameter param, final String text) {
        switch (param.getType().getSimpleName()) {
            case "int":
            case "Integer":
                return Integer.valueOf(text);
            case "double":
            case "Double":
                return Double.valueOf(text);
            case "float":
            case "Float":
                return Float.valueOf(text);
            case "String":
                return text;
            case "boolean":
            case "Boolean":
                return Boolean.valueOf(text);
        }
        return null;
    }

}
