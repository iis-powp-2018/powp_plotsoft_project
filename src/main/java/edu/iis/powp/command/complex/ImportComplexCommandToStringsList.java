package edu.iis.powp.command.complex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportComplexCommandToStringsList {

	public static List<String> getCommands(String fileName) {
		if (fileName == null)
			return new ArrayList<String>(0);

		File file = new File(fileName);
		if (!(file.exists() && file.canRead())) {
			System.err.println("Cannot access file! Non-existent or read access restricted");
			return new ArrayList<String>(0);
		}

		List<String> commandLines = new ArrayList<String>(32);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			commandLines.add(scanner.nextLine());
		}

		scanner.close();

		return commandLines;
	}
}
