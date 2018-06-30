package edu.iis.powp.command.factory;

import edu.iis.powp.command.IPlotterCommand;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DynamicParameterInputWindowController {

    private final CommandFactoryWindow commandFactoryWindow;
    private final DefaultListModel<IPlotterCommand> jListModel;
    private final JFrame frame;

    public DynamicParameterInputWindowController(CommandFactoryWindow commandFactoryWindow,
            DefaultListModel<IPlotterCommand> jListModel, JFrame frame){

        this.commandFactoryWindow = commandFactoryWindow;
        this.jListModel = jListModel;
        this.frame = frame;
    }

    void handleAddButton(Map<Parameter, JTextField> textFields, Constructor<? extends IPlotterCommand> constructor) {
        List<Object> arguments = new ArrayList<>();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.dispose();
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
