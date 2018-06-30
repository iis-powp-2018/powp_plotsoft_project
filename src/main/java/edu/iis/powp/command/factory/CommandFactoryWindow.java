package edu.iis.powp.command.factory;

import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.LineFactory;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;

public class CommandFactoryWindow extends JFrame implements WindowComponent{

    private final CommandRegistry commandRegistry;

    private static final Integer COMMON_WIDTH = 100;
    private static final Integer COMMON_WIDTH2 = 160;
    private static final Integer COMMON_HEIGHT = 30;
    private final CommandFactoryWindowController commandFactoryWindowController = new CommandFactoryWindowController(this);
    private LineAdapterPlotterDriver lineAdapterPlotterDriver;
    private DrawPanelController drawPanelController = new DrawPanelController();

    public LineAdapterPlotterDriver getLineAdapterPlotterDriver() {
        return lineAdapterPlotterDriver;
    }

    public DrawPanelController getDrawPanelController() {
        return drawPanelController;
    }

    public CommandRegistry getCommandRegistry() {

        return commandRegistry;
    }

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
        basicAddButton.addActionListener(
                actionEvent -> commandFactoryWindowController.handleBasicAddButton(jListModel, basicComboBox));
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
        complexAddButton.addActionListener(
                actionEvent -> commandFactoryWindowController.handleComplexAddButton(jListModel, complexComboBox));
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
        saveButton.addActionListener(actionEvent -> commandFactoryWindowController
                .handleSaveButton(jListModel, complexComboBox, commandNameField));
        saveButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        saveButtonConstraints.gridx = 1;
        saveButtonConstraints.gridy = 2;
        leftPanel.add(saveButton,saveButtonConstraints);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(actionEvent -> commandFactoryWindowController.handleClearButton(jListModel));
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


    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    public class ConstructorDecorator{
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
