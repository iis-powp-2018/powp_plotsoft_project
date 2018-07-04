package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669948L;
	private PlotterCommandManager commandManager;
	private DefaultListModel model;

	public CommandEditorWindow(PlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		setupContainerUIComponents();

	}

	private void setupContainerUIComponents() {
		this.setTitle("Command Editor");
		this.setSize(400, 800);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();
		String[] selections = { "green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue" };
		JList list = new JList(selections);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		content.add(list,c);
		
		JButton btnMoveUP = new JButton("Move Up");
		btnMoveUP.addActionListener((ActionEvent e) -> this.moveUp());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;


		content.add(btnMoveUP, c);

		JButton btnMoveDown = new JButton("Move Down");
		btnMoveDown.addActionListener((ActionEvent e) -> this.moveDown());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnMoveDown, c);

		JButton btnRemoveCommand = new JButton("Remove");
		btnRemoveCommand.addActionListener((ActionEvent e) -> this.removeCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;

		content.add(btnRemoveCommand, c);

		JButton btnLoadCommand = new JButton("Load Command");
		btnLoadCommand.addActionListener((ActionEvent e) -> this.loadCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;

		content.add(btnLoadCommand, c);
	}

	private void moveUp() {

	}

	private void moveDown() {

	}

	private void removeCommand() {

	}

	private void loadCommand() {

	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {

		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}

	}

}
