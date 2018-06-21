package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.features.CommandsFeature;
import powp.commandsFactory.CommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactoryEditorAdapter {

    private CommandsFactory commandsFactory;

    public CommandsFactory getCommandsFactory() {
        return commandsFactory;
    }

    public FactoryEditorAdapter()
    {
        commandsFactory = new CommandsFactory();

    }

    public void createNewCommand(DefaultListModel<String> newCommands, ArrayList<String> arguments, String commandName)
    {
        List<IPlotterCommand> list = new ArrayList<>();

        for (int i = 0; i < newCommands.getSize(); i++)
        {
            String[] args = new String[4];
            if(!arguments.get(i).equals(""))
                args =  arguments.get(i).split(" ");
            if(newCommands.get(i).equals("DrawTo"))
            {
                list.add(new DrawToCommand(Integer.parseInt(args[0]),Integer.parseInt(args[1])));

            }
            else if (newCommands.get(i).equals("setPosition"))
            {
                list.add(new SetPositionCommand(Integer.parseInt(args[0]),Integer.parseInt(args[1])));

            }
            else
            {

                try {
                    ICompoundCommand command = (ICompoundCommand) commandsFactory.getCommand(newCommands.get(i));
                    executeCommand((newCommand) command);
                } catch (IllegalFactoryObjectName illegalFactoryObjectName) {
                    illegalFactoryObjectName.printStackTrace();
                }


            }
        }

        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        manager.setCurrentCommand(list, commandName);


        try {
            commandsFactory.addCommandToFactory(new newCommand(list), commandName);
        } catch (IllegalFactoryObjectName illegalFactoryObjectName) {
            illegalFactoryObjectName.printStackTrace();
        }
    }

    private void executeCommand(newCommand command) {
    }

    private class newCommand implements ICompoundCommand
    {
        public List<IPlotterCommand> getList() {
            return list;
        }

        private List<IPlotterCommand> list;

        newCommand(List<IPlotterCommand> list)
        {
            this.list = list;
        }

        @Override
        public Iterator<IPlotterCommand> iterator() {
            return null;
        }

        @Override
        public void execute(IPlotter plotter) {

        }
    }
}
