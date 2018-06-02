package powp.commandsFactory;

import powp.commandManager.ICommand;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import java.util.HashMap;
import java.util.Map;

public class CommandsFactory implements  ICommandsFactory {
    @Override
    public ICommand getCommand(final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if (candidate == null) {
            throw new IllegalFactoryObjectName("");
        }
        return candidate;
    }

    @Override
    public ICommand cloneCommand(final String key) throws IllegalFactoryObjectName {
        return null;
    }

    @Override
    public void addCommandToFactory(ICommand command, final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if (candidate != null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.put(key, command);
        }
    }

    @Override
    public void deleteObject(final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if(candidate == null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.remove(candidate);
            // Sprawdzić czy zadziała w ogóle.
        }

    }

    public CommandsFactory() {
        commandsCollection = new HashMap<>();
    }

    private Map<String, ICommand> commandsCollection;
}
