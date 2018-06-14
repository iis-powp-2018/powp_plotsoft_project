package edu.iis.powp.command.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.io.CommandFileIOStorage;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

/**
 * Edycja command. Usuwanie elementow. Swap elementów. NIe skonczony na widoku,
 * jest backend
 * 
 * @author ola
 *
 */
public class CommandEditWindow extends JFrame implements WindowComponent {

	private PlotterCommandManager commandManager;

	private JList list;
	private int subCommandCounter = 0;
	private DefaultListModel model;
	/**
	 * 
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	/**
	 * Tworzenie okienka z layoutem i podstawowymi guzikami. Wczytanie komendy na
	 * load button
	 * 
	 * @param commandManager
	 */
	public CommandEditWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Edit");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());

		this.commandManager = commandManager;

		model = new DefaultListModel();
		list = new JList(model);
		JScrollPane pane = new JScrollPane(list);
		JButton loadButton = new JButton("Load Current Command");
		JButton removeButton = new JButton("Remove Element");


		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCurrentCommand(commandManager);
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				model.remove(selectedIndex);
				ComplexCommand currentCommand = (ComplexCommand) commandManager.getCurrentCommand();
				currentCommand.removeCommand(selectedIndex);
				subCommandCounter--;

			}
		});

		add(pane, BorderLayout.CENTER);
		add(loadButton, BorderLayout.NORTH);
		add(removeButton, BorderLayout.SOUTH);

	}

	/**
	 * Ladowanie sub polecen na liste
	 * 
	 * @param commandManager
	 */
	private void loadCurrentCommand(PlotterCommandManager commandManager) {
		model.removeAllElements();
		ComplexCommand currentCommand = (ComplexCommand) commandManager.getCurrentCommand();
		if (currentCommand != null) {
			int counter = 0;
			subCommandCounter = 0;
			Iterator<IPlotterCommand> iterator = currentCommand.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				model.addElement("Sub command " + counter);
				counter++;
				subCommandCounter++;

			}
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
