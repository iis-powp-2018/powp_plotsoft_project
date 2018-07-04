package edu.iis.powp.command.complex;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.iis.powp.command.IPlotterCommand;

public class ExportComplexCommand {
	public static void export(String fileName, ArrayList<IPlotterCommand> commandsList) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));

		for (IPlotterCommand iPlotterCommand : commandsList) {
			pw.println(iPlotterCommand.toString());
		}
		pw.close();
	}
}
