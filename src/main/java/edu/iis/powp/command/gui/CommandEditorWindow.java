package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument.Content;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669948L;
	private PlotterCommandManager commandManager;
	private DefaultListModel model;
	private IPlotterCommand currentCommand;
	private int commandCount = 0;
	private Container content;
	private JList list;
	private GridBagConstraints c;
	public CommandEditorWindow(PlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		setupContainerUIComponents();

	}

	private void setupContainerUIComponents() {
		this.setTitle("Command Editor");
		this.setSize(400, 800);
		content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		this.commandManager = commandManager;

		c = new GridBagConstraints();
		String[] selections = { "green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue","green", "red", "orange", "dark blue" };
		list = new JList(selections);
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
		commandManager.setCurrentCommand(new ComplexCommand(commandManager.getCurrentCommand()));
		loadCurrentSubCommands(commandManager);
	}

	private void loadCurrentSubCommands(PlotterCommandManager commandManager) {
		model.removeAllElements();
		ICompoundCommand currentCommand = commandManager.getCurrentCommand();
		if (currentCommand != null) {
			String[] commands = null;
			commandCount = 0;
			int count = 0;
			Iterator<IPlotterCommand> iterator = currentCommand.iterator();
			content.remove(list);
			while (iterator.hasNext()) {
				IPlotterCommand buff = iterator.next();
				content.remove(list);
				commands[count] = "Command " + count + " :" + buff.toString();
				count++;
				commandCount++;
			}
			list = new JList(commands);
			content.add(list,c);
		}
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
