package edu.iis.powp.commandtransformer.decorator.gui;

import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.commandtransformer.decorator.gui.components.CommandTransformerCreatorComponent;
import edu.iis.powp.events.SelectRunCurrentCommandOptionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Class which provides simple GUI for CommandTransformer plugin
 */
public class CommandTransformerCreatorWindow extends JFrame implements WindowComponent {
    /**
     * Initial width of the CommandTransformerCreator window
     */
    private static final int WINDOW_WIDTH = 400;
    /**
     * Initial height of the CommandTransformerCreator window
     */
    private static final int WINDOW_HEIGHT = 600;
    /**
     * Last rendered row coordinate
     */
    private int gridY;
    /**
     * Container for GUI components
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
     * Method used to add new GUI component
     * @param title title of the new added component
     * @param component component to be added
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
     * Built-in component which provides "run current command" button
     */
    private class RunCommandComponent extends CommandTransformerCreatorComponent {
        private SelectRunCurrentCommandOptionListener listener;

        RunCommandComponent(DriverManager driverManager) {
            listener = new SelectRunCurrentCommandOptionListener(driverManager);
        }

        @Override
        protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
            JButton runCommandButton = super.addApplyButton(panel, constraints);
            runCommandButton.setText("Run command");
            return runCommandButton;
        }

        @Override
        protected void setApplyButtonActions(ActionEvent e) {
            super.setApplyButtonActions(e);
            listener.actionPerformed(e);
        }
    }
}
