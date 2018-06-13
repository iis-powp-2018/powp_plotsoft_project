package edu.iis.powp.features;

import edu.iis.powp.command.manager.LoggerCommandChangeObserver;
import edu.iis.powp.command.manager.PlotterCommandManager;

import java.util.ArrayList;

public class CommandsFeature {

	private static PlotterCommandManager commandManager;
	public static ArrayList<String> commandList = new ArrayList<>();

	public static void setupCommandManager() {
		commandManager = new PlotterCommandManager();

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
	}

	/**
	 * Get manager of application plotter command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static PlotterCommandManager getPlotterCommandManager() {
		return commandManager;
	}
}
