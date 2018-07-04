package edu.iis.powp.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.complex.CompoundCommand;
import edu.iis.powp.command.complex.ExportCompoundCommand;
import edu.iis.powp.command.complex.ImportComplexCommandToStringsList;

public class SelectTestFigure2OptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectTestFigure2OptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FiguresJoe.figureScript2(driverManager.getCurrentPlotter());

		ArrayList<IPlotterCommand> listOfCommands = new ArrayList<IPlotterCommand>();
		listOfCommands.add(new SetPositionCommand(0, 0));
		listOfCommands.add(new SetPositionCommand(10, 0));
		listOfCommands.add(new SetPositionCommand(0, 10));
		listOfCommands.add(new SetPositionCommand(20, 0));
		listOfCommands.add(new SetPositionCommand(0, 20));
		ComplexCommand commands = new ComplexCommand(listOfCommands);
		CompoundCommand compoundCommand = new CompoundCommand();
		compoundCommand.addComplexCommand(commands);
		compoundCommand.addComplexCommand(commands);
		compoundCommand.addComplexCommand(commands);
		compoundCommand.addComplexCommand(commands);
		try {
			ExportCompoundCommand.export("exportedlistofcommands.txt", compoundCommand);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		compoundCommand = new CompoundCommand();

		compoundCommand = ImportComplexCommandToStringsList.getCommands("exportedlistofcommands.txt");
		try {
			ExportCompoundCommand.export("listofcommandsafterimport.txt", compoundCommand);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}