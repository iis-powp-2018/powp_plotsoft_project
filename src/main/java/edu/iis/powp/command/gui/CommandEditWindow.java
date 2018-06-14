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

	private static final long serialVersionUID = 9204679248304669948L;
	
	private final PlotterCommandManager commandManager;
	
	private JList list;
	private DefaultListModel model;
	private int subCommandCounter = 0;
	
	

	/**
	 * Tworzenie okienka z layoutem i podstawowymi guzikami. Wczytanie komendy na
	 * load button
	 * 
	 * @param commandManager
	 */
	public CommandEditWindow(PlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		setupContainerUIComponents();

	}

	/**
	 * Ustawienie komponentów w oknie
	 */
	private void setupContainerUIComponents() {
		this.setTitle("Command Edit");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		model = new DefaultListModel();
		list = new JList(model);
		JScrollPane pane = new JScrollPane(list);
		add(pane, BorderLayout.CENTER);
		setupButtonWithActions();
	}

	/**
	 * skonfugirowanie guzików z akcjami
	 */
	private void setupButtonWithActions() {
		setupLoadCommandItemListButton();
		setupRemoveItemFromListButton();
	}

	/**
	 * Stworznie guzika do usuwania elementu
	 */
	private void setupRemoveItemFromListButton() {
		JButton removeButton = new JButton("Remove Element");
		removeButton.addActionListener(new RemoveButtonListener(commandManager));
		add(removeButton, BorderLayout.SOUTH);
	}

	/**
	 * Stworzenie guzika do ladowania komendy
	 */
	private void setupLoadCommandItemListButton() {
		JButton loadButton = new JButton("Load Current Command");
		loadButton.addActionListener(new LoadActionListener(commandManager));
		add(loadButton, BorderLayout.NORTH);
	}

	/**
	 * Ladowanie sub polecen na liste
	 * 
	 * @param commandManager
	 */
	private void loadCurrentSubCommands(PlotterCommandManager commandManager) {
		model.removeAllElements();
		ICompoundCommand currentCommand =commandManager.getCurrentCommand();
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

	private final class LoadActionListener implements ActionListener {
		private final PlotterCommandManager commandManager;

		private LoadActionListener(PlotterCommandManager commandManager) {
			this.commandManager = commandManager;
		}

		public void actionPerformed(ActionEvent e) {
			loadCurrentSubCommands(commandManager);
		}
	}

	private final class RemoveButtonListener implements ActionListener {
		private final PlotterCommandManager commandManager;

		private RemoveButtonListener(PlotterCommandManager commandManager) {
			this.commandManager = commandManager;
		}

		public void actionPerformed(ActionEvent e) {
			int selectedIndex = list.getSelectedIndex();
			model.remove(selectedIndex);
			ComplexCommand currentCommand = (ComplexCommand) commandManager.getCurrentCommand();
			currentCommand.removeCommand(selectedIndex);
			subCommandCounter--;

		}
	}

}
