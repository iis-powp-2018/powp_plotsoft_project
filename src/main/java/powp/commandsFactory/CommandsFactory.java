package powp.commandsFactory;

import java.util.ArrayList;

import edu.iis.powp.command.IPlotterCommand;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsFactory implements  ICommandsFactory {
    @Override
    public IPlotterCommand getCommand(final String key) throws IllegalFactoryObjectName {
        IPlotterCommand candidate = commandsCollection.get(key);
        if (candidate == null) {
            throw new IllegalFactoryObjectName("");
        }
        return candidate;
    }


    @Override
    public void addCommandToFactory(IPlotterCommand command, final String key) throws IllegalFactoryObjectName {
        IPlotterCommand candidate = commandsCollection.get(key);
        if (candidate != null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.put(key, command);
        }
    }

    @Override
    public void deleteObject(final String key) throws IllegalFactoryObjectName {
        IPlotterCommand candidate = commandsCollection.get(key);
        if(candidate == null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.remove(candidate);
        }

    }

    public CommandsFactory() {
        commandsCollection = new HashMap<>();
    }

    public List<String> getRegisteredCommands() {
         return new ArrayList<>(commandsCollection.keySet());
    }
    private Map<String, IPlotterCommand> commandsCollection;
}
