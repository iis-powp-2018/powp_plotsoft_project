package edu.iis.powp.command.complex;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.iis.powp.command.IPlotterCommand;

public class ExportCompoundCommand {
	public static void export(String fileName, CompoundCommand commandsList) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));

		for (ComplexCommand complexCommand : commandsList.getComplexCommandList()) {
			for (IPlotterCommand iPlotterCommand : complexCommand.getListOfCommands()) {
				pw.println(iPlotterCommand.toString());
			}
			pw.println("END");
		}
		pw.close();
	}
}
