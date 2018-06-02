package edu.iis.powp.commandtransformer.decorator.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Define common methods for building components which manage transformation's commands
 */
public abstract class CommandTransformerCreatorComponent {

    /**
     * Final method which returns JPanel with content set in addContent method and one button
     *
     * @return JPanel
     */
    public final JPanel buildComponent() {
        JPanel panel = constructJPanel();
        addContent(panel, constructElementGridBagConstraints());
        JButton applyButton = addApplyButton(panel, constructApplyButtonGridBagConstraints());
        setApplyButtonActionListeners(applyButton);
        return panel;
    }

    /**
     * Method which return JPanel with set layout as GridBagLayout.
     *
     * @return JPanel
     */
    protected JPanel constructJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    /**
     * Method which specifies constrains for single element
     * @return GridBagConstraints
     */
    protected GridBagConstraints constructElementGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        return constraints;
    }

    /**
     * Method which specifies constrains for apply button
     * @return GridBagConstraints
     */
    protected GridBagConstraints constructApplyButtonGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.0;
        constraints.ipady = 20;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 0);
        return constraints;
    }

    /**
     * Method which add new component
     * @param panel the panel to which add new component
     * @param constraints the constraints for added component
     */
    protected void addContent(JPanel panel, GridBagConstraints constraints) {

    }

    /**
     * Method which add button with text set to "Apply command"
     * @param panel the panel to which add button
     * @param constraints the constraints for added button
     * @return JButton
     */
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = new JButton("Apply command");
        panel.add(applyButton, constraints);
        return applyButton;
    }

    /**
     * Method which set action listener to apply button
     *
     * @param applyButton the button to which add listener
     */
    protected void setApplyButtonActionListeners(JButton applyButton) {

    }
}
