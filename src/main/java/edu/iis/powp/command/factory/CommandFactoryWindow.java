package edu.iis.powp.command.factory;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.features.DrawerFeature;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Map;

public class CommandFactoryWindow extends JFrame implements WindowComponent{

    private final CommandRegistry commandRegistry;

    private static final Integer COMMON_WIDTH = 100;
    private static final Integer COMMON_WIDTH2 = 160;
    private static final Integer COMMON_HEIGHT = 30;


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
        JPanel jPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        jPanel.setLayout(gridBagLayout);
        GridBagConstraints jPanelConstraints = new GridBagConstraints();
        jPanelConstraints.fill = 1;
        jPanelConstraints.gridheight = 1;
        jPanelConstraints.gridx = 0;
        jPanelConstraints.gridy = 0;
        jPanelConstraints.weighty = 1;
        this.getContentPane().add(jPanel, jPanelConstraints);

        JComboBox basicComboBox = new JComboBox();
        basicComboBox.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        for (Constructor<?> constructor : commandRegistry.getRegisteredBasicCommands()) {
            basicComboBox.addItem(new ConstructorDecorator(constructor));
        }
        GridBagConstraints basicComboBoxConstraints = new GridBagConstraints();
        basicComboBoxConstraints.gridx = 0;
        basicComboBoxConstraints.gridy = 0;
        jPanel.add(basicComboBox,basicComboBoxConstraints);

        JButton basicAddButton = new JButton("Add");
        basicAddButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints basicAddButtonConstraints = new GridBagConstraints();
        basicAddButtonConstraints.gridx = 1;
        basicAddButtonConstraints.gridy = 0;
        jPanel.add(basicAddButton,basicAddButtonConstraints);

        JComboBox complexComboBox = new JComboBox();
        complexComboBox.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        for(Map.Entry<ICompoundCommand, String> complexCommand : commandRegistry.getRegisteredComplexCommands().entrySet()){
            complexComboBox.addItem(new ComplexCommandDecorator(complexCommand.getKey(), complexCommand.getValue()));
        }
        GridBagConstraints complexComboBoxConstraints = new GridBagConstraints();
        complexComboBoxConstraints.gridx = 0;
        complexComboBoxConstraints.gridy = 1;
        jPanel.add(complexComboBox,complexComboBoxConstraints);

        JButton complexAddButton = new JButton("Add");
        complexAddButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints complexAddButtonConstraints = new GridBagConstraints();
        complexAddButtonConstraints.gridx = 1;
        complexAddButtonConstraints.gridy = 1;
        jPanel.add(complexAddButton,complexAddButtonConstraints);

        JTextField commandNameField = new JTextField();
        commandNameField.setPreferredSize(new Dimension(COMMON_WIDTH2, COMMON_HEIGHT));
        GridBagConstraints commandNameFieldConstraints = new GridBagConstraints();
        commandNameFieldConstraints.gridx = 0;
        commandNameFieldConstraints.gridy = 2;
        jPanel.add(commandNameField,commandNameFieldConstraints);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        saveButtonConstraints.gridx = 1;
        saveButtonConstraints.gridy = 2;
        jPanel.add(saveButton,saveButtonConstraints);

        JTable jTable = new JTable();
        GridBagConstraints jTableConstraints = new GridBagConstraints();
        jTableConstraints.fill = 1;
        jTableConstraints.gridheight = 1;
        jTableConstraints.gridx = 0;
        jTableConstraints.gridy = 1;
        jTableConstraints.weighty = 1;
        this.getContentPane().add(jTable, jTableConstraints);
        JScrollPane commandListScrollPane = new JScrollPane();
        GridBagConstraints gbcCommandListScrollPane = new GridBagConstraints();
        gbcCommandListScrollPane.fill = 1;
        gbcCommandListScrollPane.gridheight = 2;
        gbcCommandListScrollPane.gridx = 1;
        gbcCommandListScrollPane.gridy = 0;
        this.getContentPane().add(commandListScrollPane, gbcCommandListScrollPane);
        this.freePanel = new JPanel();
        commandListScrollPane.setViewportView(this.freePanel);
        DrawerFeature.getDrawerController().initialize(freePanel);
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
        final Constructor<?> constructor;

        public ConstructorDecorator(final Constructor<?> constructor) {
            this.constructor = constructor;
        }

        public Constructor<?> getConstructor() {
            return constructor;
        }

        @Override
        public String toString() {
            return constructor.getDeclaringClass().getSimpleName();
        }
    }

    private class ComplexCommandDecorator {
        final ICompoundCommand compoundCommand;
        final String name;

        public ComplexCommandDecorator(final ICompoundCommand compoundCommand, final String name) {
            this.compoundCommand = compoundCommand;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
