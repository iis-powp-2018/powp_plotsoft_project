package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.complex.CompoundCommand;
import edu.iis.powp.command.complex.ExportCompoundCommand;
import edu.iis.powp.command.complex.ImportComplexCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private PlotterCommandManager commandManager;

	private JTextArea currentCommandField;

	private String observerListString;
	private JTextArea observerListField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		JButton btnImportCommand = new JButton("Import command");
		btnImportCommand.addActionListener((ActionEvent e) -> {
			try {
				this.importCommand();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnImportCommand, c);

		JButton btnExportCommand = new JButton("Export command");
		btnExportCommand.addActionListener((ActionEvent e) -> {
			try {
				this.exportCommand();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnExportCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearObservers, c);
	}

	private void importCommand() throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		CompoundCommand newCommand = ImportComplexCommand.getCommands(fileChooser.getSelectedFile().getAbsolutePath());	
		commandManager.setCurrentCommand(compoundToComplex(newCommand));

	}

	private void exportCommand() throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(this);
		ComplexCommand complex = new ComplexCommand(commandManager.getCurrentCommand());
		CompoundCommand currentCommand = new CompoundCommand(new ArrayList<ComplexCommand>());
		currentCommand.addComplexCommand(complex);
		ExportCompoundCommand.export(fileChooser.getSelectedFile().getAbsolutePath(), currentCommand);
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty()) {
			observerListString = "No observers loaded";
		}

		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}
	
	private ComplexCommand compoundToComplex(CompoundCommand compCmd) {
        ComplexCommand temp = null;
        List<ComplexCommand> tempList = new ArrayList<ComplexCommand>(compCmd.getComplexCommandList());
        List<IPlotterCommand> tempListIPlot = new ArrayList<IPlotterCommand>();
        for(ComplexCommand cc : tempList) {
                tempListIPlot.addAll(cc.getListOfCommands());
            }
        temp = new ComplexCommand(tempListIPlot);
        return temp;
        }

}
