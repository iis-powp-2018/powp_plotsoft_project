package edu.iis.powp.command.edxternalData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.complex.CompoundCommand;

public class ImportCompoundCommand {

	public static CompoundCommand getCommands(String fileName) {
		ArrayList<IPlotterCommand> listOfCommands = new ArrayList<IPlotterCommand>();
		String tempLine;
		ComplexCommand commands = new ComplexCommand(listOfCommands);
		CompoundCommand compoundCommand = new CompoundCommand(new ArrayList<ComplexCommand>());

		if (fileName == null)
			return new CompoundCommand(new ArrayList<ComplexCommand>());

		File file = new File(fileName);
		if (!(file.exists() && file.canRead())) {
			System.err.println("Cannot access file! Non-existent or read access restricted");
			return new CompoundCommand(new ArrayList<ComplexCommand>());
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNext()) {
			tempLine = scanner.next();
			if (tempLine.contains("END")) {
				commands = new ComplexCommand(listOfCommands);
				compoundCommand.addComplexCommand(commands);
				commands = new ComplexCommand(new ArrayList<IPlotterCommand>());
			} else if (tempLine.contains("SetPositionCommand")) {
				listOfCommands.add(new SetPositionCommand(scanner.nextInt(), scanner.nextInt()));
			} else if (tempLine.contains("DrawToCommand")) {
				listOfCommands.add(new DrawToCommand(scanner.nextInt(), scanner.nextInt()));
			}
		}
		scanner.close();
		return compoundCommand;
	}

}
