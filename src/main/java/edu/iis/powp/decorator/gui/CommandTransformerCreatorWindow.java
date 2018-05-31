package edu.iis.powp.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.decorator.gui.components.CommandTransformerCreatorComponent;
import edu.iis.powp.decorator.gui.events.RunCommandListener;

import javax.swing.*;
import java.awt.*;

/**
 * Class which provides simple interface for adding new tranformed commands to GUI
 */
public class CommandTransformerCreatorWindow extends JFrame implements WindowComponent {
    /**
     * Field which indicates width of the window
     */
    private static final int WINDOW_WIDTH = 400;
    /**
     * Field which indicates height of the window
     */
    private static final int WINDOW_HEIGHT = 600;
    /**
     * Field which handles number of rows
     */
    private int gridY;
    /**
     * Container for included components
     */
    private final JPanel content;

    public CommandTransformerCreatorWindow(DriverManager driverManager) {
        this.content = new JPanel();
        this.content.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setWheelScrollingEnabled(true);

        this.setTitle("Command Transformer Creator");
        this.setContentPane(scrollPane);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.gridY = 0;

        addComponent("Command runner", new RunCommandComponent(driverManager));
    }

    /**
     * Method which add new component
     * @param title title of the new added component
     * @param component specific component to add
     */
    public void addComponent(String title, CommandTransformerCreatorComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = gridY;

        JPanel builtComponent = component.buildComponent();
        builtComponent.setBorder(BorderFactory.createTitledBorder(title));
        content.add(builtComponent, constraints);
        gridY++;
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    /**
     * Inner class which is responsible for run added components
     */
    private class RunCommandComponent extends CommandTransformerCreatorComponent {

        /**
         * Field which is responsible for storage current driverManager of the application
         */
        private DriverManager driverManager;

        RunCommandComponent(DriverManager driverManager) {
            this.driverManager = driverManager;
        }

        @Override
        protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
            JButton runCommandButton = super.addApplyButton(panel, constraints);
            runCommandButton.setText("Run command");
            runCommandButton.addActionListener(new RunCommandListener(driverManager));
            return runCommandButton;
        }
    }
}
