package edu.iis.powp.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.DrawToCommand;
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
		// FiguresJoe.figureScript2(driverManager.getCurrentPlotter());

		ArrayList<IPlotterCommand> listOfCommands = new ArrayList<IPlotterCommand>();
		CompoundCommand compoundCommand = new CompoundCommand(new ArrayList<ComplexCommand>());
		listOfCommands.add(new SetPositionCommand(0, 0));
		listOfCommands.add(new SetPositionCommand(10, 0));
		listOfCommands.add(new SetPositionCommand(0, 10));
		listOfCommands.add(new DrawToCommand(20, 0));
		listOfCommands.add(new SetPositionCommand(0, 20));
		ComplexCommand commands = new ComplexCommand(listOfCommands);
		compoundCommand.addComplexCommand(commands);
		listOfCommands = new ArrayList<IPlotterCommand>();
		listOfCommands.add(new SetPositionCommand(0, 0));
		listOfCommands.add(new SetPositionCommand(10, 0));
		listOfCommands.add(new SetPositionCommand(0, 100));
		listOfCommands.add(new DrawToCommand(50, 0));
		listOfCommands.add(new SetPositionCommand(0, 20));
		commands = new ComplexCommand(listOfCommands);
		compoundCommand.addComplexCommand(commands);

		try {
			ExportCompoundCommand.export("exportedlistofcommands.txt", compoundCommand);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		CompoundCommand compoundCommand2 = new CompoundCommand(new ArrayList<ComplexCommand>());

		compoundCommand2 = ImportComplexCommandToStringsList.getCommands("exportedlistofcommands.txt");

		compoundCommand2.execute(driverManager.getCurrentPlotter());

		try {
			ExportCompoundCommand.export("listofcommandsafterimport.txt", compoundCommand);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}