package powp.commandManager;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import powp.commandManager.exceptions.FactoryNullPointerException;
import powp.commandManager.exceptions.IllegalCommandArguments;
import powp.commandManager.exceptions.IllegalCommandName;
import powp.commandManager.exceptions.IllegalRegisteredObjectName;
import powp.commandsFactory.ICommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager implements ICommandsManager {
    @Override
    public void sendMessage(String commandSequence) throws IllegalCommandArguments, IllegalCommandName, IllegalFactoryObjectName {
        final  Iterable<String> iterable;
        final String[] arguments;
        final ICommand command;
        IReceiver candidate;

        if(commandsFactory == null) {
            throw new FactoryNullPointerException("");
        }

        iterable = splitter.split(commandSequence);
        arguments = Iterables.toArray(iterable, String.class);

        if(arguments.length < 2) {
            throw new IllegalCommandArguments("");
        }

        candidate = receivers.get(arguments[0]);
        if(candidate == null || (!candidate.commandIsRegistered(arguments[1]))) {
            throw new IllegalCommandName("");
        }

        command = commandsFactory.getCommand(arguments[1]);

        if(command == null) {
            throw new IllegalFactoryObjectName("");
        }

        command.execute(candidate, arguments);
    }

    @Override
    public void registerObject(IReceiver receiver, String objectName) throws IllegalRegisteredObjectName {
        if(keyIsBusy(objectName) || (!isCorrectName(objectName))) {
            throw new IllegalRegisteredObjectName("");
        }

        receivers.put(objectName, receiver);
    }

    @Override
    public void unregisterObject(IReceiver command, String objectName) throws IllegalCommandArguments {
        IReceiver referenceToDeleteObject;
        if(command == null || objectName == null) {
            throw new IllegalCommandArguments("");
        }
        referenceToDeleteObject = receivers.get(objectName);
        if(referenceToDeleteObject == null || referenceToDeleteObject != command) {
            throw new IllegalCommandArguments("");
        }
        receivers.remove(objectName);
    }

    @Override
    public void addObjectToGroup(ICommand command, String groupName) throws IllegalRegisteredObjectName {

    }

    @Override
    public void destroyObjectsGroup(String groupName) throws IllegalRegisteredObjectName {

    }

    @Override
    public void registerCommandsFactory(ICommandsFactory factory) {
        commandsFactory = factory;
    }


    private boolean keyIsBusy(String key) {
        return (receivers.get(key) != null);
    }

    public CommandsManager()
    {
        receivers = new HashMap<>();
    }

    public boolean isCorrectName(String key) {

        return true;
    }

    private static Splitter splitter = Splitter.on(' ').trimResults().omitEmptyStrings();
    private ICommandsFactory commandsFactory;
    private Map<String, IReceiver> receivers;
}
