package edu.iis.powp.commandtransformer.decorator.gui.components;

import edu.iis.powp.commandtransformer.decorator.gui.events.ApplyCommandListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Base class that defines GUI single component for CommandTransformerCreator window
 */
public abstract class CommandTransformerCreatorComponent<T extends ApplyCommandListener> {
    protected T applyButtonClickListener;

    public CommandTransformerCreatorComponent(T applyButtonClickListener) {
        this.applyButtonClickListener = applyButtonClickListener;
    }

    protected CommandTransformerCreatorComponent() {
    }

    /**
     * Template method that builds GUI component
     *
     * @return JPanel built component
     */
    public final JPanel buildComponent() {
        JPanel panel = constructJPanel();
        addContent(panel, constructElementGridBagConstraints());
        JButton applyButton = addApplyButton(panel, constructApplyButtonGridBagConstraints());
        applyButton.addActionListener(this::setApplyButtonActions);
        return panel;
    }

    /**
     * Prepare basic container for component.
     *
     * @return JPanel component container
     */
    protected JPanel constructJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    /**
     * Method which specifies constraints for single element
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
     * Method which specifies constraints for apply button
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
     * Method used to inject main content of component
     * @param panel container for newly added content
     * @param constraints the constraints used to build elements
     */
    protected void addContent(JPanel panel, GridBagConstraints constraints) {

    }

    /**
     * Method used to create and configure apply button (only its GUI)
     * @param panel container for newly added content
     * @param constraints the constraints used to build button
     * @return JButton apply button
     */
    protected JButton addApplyButton(JPanel panel, GridBagConstraints constraints) {
        JButton applyButton = new JButton("Apply command");
        panel.add(applyButton, constraints);
        return applyButton;
    }

    /**
     * Add actions to perform after click on apply button (i.e. fire listener, reset components state)
     *
     * @param e event that fired the listener
     */
    protected void setApplyButtonActions(ActionEvent e) {
        if (applyButtonClickListener != null) {
            applyButtonClickListener.actionPerformed(e);
        }
    }
}
