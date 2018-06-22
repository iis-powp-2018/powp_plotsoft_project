package edu.iis.powp.command.factory;

import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.LineFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandFactoryWindow extends JFrame implements WindowComponent{

    private final CommandRegistry commandRegistry;

    private static final Integer COMMON_WIDTH = 100;
    private static final Integer COMMON_WIDTH2 = 160;
    private static final Integer COMMON_HEIGHT = 30;
    private LineAdapterPlotterDriver lineAdapterPlotterDriver;
    private DrawPanelController drawPanelController = new DrawPanelController();


    public CommandFactoryWindow(final CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
        this.initialize();
    }

    private static final long serialVersionUID = -7773470008348070296L;
    private JPanel freePanel = null;

    private void initialize() {
        this.setTitle("Command Factory");
        this.setBounds(50, 100, 840, 520);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        GridBagLayout mainGridBagLayout = new GridBagLayout();
        mainGridBagLayout.columnWidths = new int[]{0, 540};
        mainGridBagLayout.rowHeights = new int[2];
        mainGridBagLayout.columnWeights = new double[]{1.0D, 4.9E-324D};
        mainGridBagLayout.rowWeights = new double[]{0.0D, 1.0D};
        this.getContentPane().setLayout(mainGridBagLayout);
        JPanel leftPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        leftPanel.setLayout(gridBagLayout);
        GridBagConstraints leftPanelConstraints = new GridBagConstraints();
        leftPanelConstraints.fill = 1;
        leftPanelConstraints.gridheight = 1;
        leftPanelConstraints.gridx = 0;
        leftPanelConstraints.gridy = 0;
        leftPanelConstraints.weighty = 1;
        this.getContentPane().add(leftPanel, leftPanelConstraints);

        JList jList = new JList();
        JScrollPane listScrollPane = new JScrollPane();
        listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScrollPane.setViewportView(jList);

        listScrollPane.setPreferredSize(new Dimension(0, 520/2));
        DefaultListModel<IPlotterCommand> jListModel = new DefaultListModel<>();
        jList.setModel(jListModel);
        GridBagConstraints jListConstraints = new GridBagConstraints();
        jListConstraints.fill = 1;
        jListConstraints.gridheight = 1;
        jListConstraints.gridx = 0;
        jListConstraints.gridy = 1;
        jListConstraints.weighty = 1;
        this.getContentPane().add(listScrollPane, jListConstraints);


        JComboBox<ConstructorDecorator> basicComboBox = new JComboBox<>();
        basicComboBox.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        for (Constructor<? extends IPlotterCommand> constructor : commandRegistry.getRegisteredBasicCommands()) {
            basicComboBox.addItem(new ConstructorDecorator(constructor));
        }
        GridBagConstraints basicComboBoxConstraints = new GridBagConstraints();
        basicComboBoxConstraints.gridx = 0;
        basicComboBoxConstraints.gridy = 0;
        leftPanel.add(basicComboBox,basicComboBoxConstraints);

        JButton basicAddButton = new JButton("Add");
        basicAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setBounds(100,100,200,100);
                ConstructorDecorator selectedItem = (ConstructorDecorator) basicComboBox.getSelectedItem();
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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        List<Object> arguments = new ArrayList<>();
                        for (Map.Entry<Parameter, JTextField> parameterJTextFieldEntry : textFields.entrySet()) {
                            Parameter param = parameterJTextFieldEntry.getKey();
                            JTextField value = parameterJTextFieldEntry.getValue();
                            Object paramValue = convertTextFieldValueToArgument(param, value.getText());
                            arguments.add(paramValue); //FIXME
                        }
                        try {
                            IPlotterCommand element = constructor.newInstance(arguments.toArray());
                            element.execute(lineAdapterPlotterDriver);
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
        });
        basicAddButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints basicAddButtonConstraints = new GridBagConstraints();
        basicAddButtonConstraints.gridx = 1;
        basicAddButtonConstraints.gridy = 0;
        leftPanel.add(basicAddButton,basicAddButtonConstraints);

        JComboBox complexComboBox = new JComboBox();
        complexComboBox.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        for(ICompoundCommand complexCommand : commandRegistry.getRegisteredComplexCommands()){
            complexComboBox.addItem(complexCommand);
        }
        GridBagConstraints complexComboBoxConstraints = new GridBagConstraints();
        complexComboBoxConstraints.gridx = 0;
        complexComboBoxConstraints.gridy = 1;
        leftPanel.add(complexComboBox,complexComboBoxConstraints);

        JButton complexAddButton = new JButton("Add");
        complexAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ICompoundCommand selectedItem = (ICompoundCommand) complexComboBox.getSelectedItem();
                jListModel.addElement(selectedItem);
                selectedItem.execute(lineAdapterPlotterDriver);
            }
        });
        complexAddButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints complexAddButtonConstraints = new GridBagConstraints();
        complexAddButtonConstraints.gridx = 1;
        complexAddButtonConstraints.gridy = 1;
        leftPanel.add(complexAddButton,complexAddButtonConstraints);

        JTextField commandNameField = new JTextField();
        commandNameField.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        GridBagConstraints commandNameFieldConstraints = new GridBagConstraints();
        commandNameFieldConstraints.gridx = 0;
        commandNameFieldConstraints.gridy = 2;
        leftPanel.add(commandNameField,commandNameFieldConstraints);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String commandName = commandNameField.getText();
                CommandFactory commandFactory = CommandFactory.create(commandName);
                Enumeration<IPlotterCommand> elements = jListModel.elements();
                while (elements.hasMoreElements()){
                    IPlotterCommand command = elements.nextElement();
                    commandFactory.addCommand(command);
                }
                ICompoundCommand newCommand = commandFactory.build();
                commandRegistry.registerComplexCommand(newCommand);
                complexComboBox.addItem(newCommand);
                commandNameField.setText("");
                jListModel.clear();
                drawPanelController.clearPanel();
            }
        });
        saveButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        saveButtonConstraints.gridx = 1;
        saveButtonConstraints.gridy = 2;
        leftPanel.add(saveButton,saveButtonConstraints);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                jListModel.clear();
                drawPanelController.clearPanel();
            }
        });
        clearButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints clearButtonConstraints = new GridBagConstraints();
        clearButtonConstraints.gridx = 1;
        clearButtonConstraints.gridy = 3;
        leftPanel.add(clearButton,clearButtonConstraints);


        JScrollPane commandListScrollPane = new JScrollPane();
        GridBagConstraints gbcCommandListScrollPane = new GridBagConstraints();
        gbcCommandListScrollPane.fill = 1;
        gbcCommandListScrollPane.gridheight = 2;
        gbcCommandListScrollPane.gridx = 1;
        gbcCommandListScrollPane.gridy = 0;
        this.getContentPane().add(commandListScrollPane, gbcCommandListScrollPane);
        this.freePanel = new JPanel();
        commandListScrollPane.setViewportView(this.freePanel);

        drawPanelController.initialize(freePanel);
        lineAdapterPlotterDriver = new LineAdapterPlotterDriver(drawPanelController, LineFactory.getBasicLine(), "Command Factory Plotter");
    }

    private Object convertTextFieldValueToArgument(final Parameter param, final String text) {
        switch (param.getType().getSimpleName()){
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

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    private class ConstructorDecorator{
        final Constructor<? extends IPlotterCommand> constructor;

        ConstructorDecorator(final Constructor<? extends IPlotterCommand> constructor) {
            this.constructor = constructor;
        }

        public Constructor<? extends IPlotterCommand> getConstructor() {
            return constructor;
        }

        @Override
        public String toString() {
            return constructor.getDeclaringClass().getSimpleName();
        }
    }
}
