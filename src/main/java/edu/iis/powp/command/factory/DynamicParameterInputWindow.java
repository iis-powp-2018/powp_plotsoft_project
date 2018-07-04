package edu.iis.powp.command.factory;

import edu.iis.powp.command.IPlotterCommand;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicParameterInputWindow {

    private final JComboBox<CommandFactoryWindow.ConstructorDecorator> basicComboBox;
    private final DynamicParameterInputWindowController dynamicParameterInputWindowController;
    private JFrame frame;

    public DynamicParameterInputWindow(CommandFactoryWindow commandFactoryWindow,
            DefaultListModel<IPlotterCommand> jListModel, JComboBox<CommandFactoryWindow.ConstructorDecorator> basicComboBox){
        this.basicComboBox = basicComboBox;
        this.frame = new JFrame();
        this.dynamicParameterInputWindowController = new DynamicParameterInputWindowController(commandFactoryWindow, jListModel, frame);
        initialize();
    }

    private void initialize() {

        frame.setBounds(100, 100, 200, 100);
        CommandFactoryWindow.ConstructorDecorator selectedItem = (CommandFactoryWindow.ConstructorDecorator) basicComboBox
                .getSelectedItem();
        Constructor<? extends IPlotterCommand> constructor = selectedItem.getConstructor();
        frame.setLayout(new GridLayout(constructor.getParameterCount() + 1, 2));
        Map<Parameter, JTextField> textFields = new LinkedHashMap<>();
        for (Parameter parameter : constructor.getParameters()) {
            JLabel label = new JLabel(parameter.getName());
            frame.add(label);
            JTextField field = new JTextField();
            frame.add(field);
            textFields.put(parameter, field);
        }
        JButton button = new JButton("Add");
        button.addActionListener(
                actionEvent -> dynamicParameterInputWindowController.handleAddButton(textFields, constructor));
        frame.add(button);
        frame.setVisible(true);
    }

}
