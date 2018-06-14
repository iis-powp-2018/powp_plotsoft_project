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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsManager implements ICommandsManager {
    @Override
    public final void sendMessage(final String commandSequence) throws IllegalCommandArguments, IllegalCommandName, IllegalFactoryObjectName {
        final  Iterable<String> iterable;
        final String[] arguments;
        final ICommand command;
        IReceiver candidate;

        if(commandsFactory == null) {
            throw new FactoryNullPointerException("");
        }

        if(!isCorrectCommandSequence(commandSequence)) {
            throw new IllegalCommandArguments("");
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
    public void registerObject(IReceiver receiver, final String objectName) throws IllegalRegisteredObjectName {
        if(keyIsBusy(objectName) || (!isCorrectCommandSequence(objectName))) {
            throw new IllegalRegisteredObjectName("");
        }
        receiver.setTerminalHandle(this);
        receiver.setObjectName(objectName);
        receivers.put(objectName, receiver);
    }

    @Override
    public void unregisterObject(IReceiver command, final String objectName) throws IllegalCommandArguments {
        IReceiver referenceToDeleteObject;

        if(command == null || (!isCorrectCommandSequence(objectName))) {
            throw new IllegalCommandArguments("");
        }

        referenceToDeleteObject = receivers.get(objectName);
        if(referenceToDeleteObject == null || referenceToDeleteObject != command) {
            throw new IllegalCommandArguments("");
        }
        receivers.remove(objectName);
    }

    @Override
    public void addObjectToGroup(IReceiver receiver, final String groupName) throws IllegalRegisteredObjectName {

    }

    @Override
    public void destroyObjectsGroup(final String groupName) throws IllegalRegisteredObjectName {

    }

    @Override
    public void registerCommandsFactory(ICommandsFactory factory) {
        commandsFactory = factory;
    }

    private boolean keyIsBusy(final String key) {
        return (receivers.get(key) != null);
    }

    public CommandsManager() {
        receivers = new HashMap<>();
    }

    protected boolean isCorrectCommandSequence (final String key) {
        if(key == null) {
            return false;
        }
        matcher = pattern.matcher(key);
        return matcher.matches();
    }

    public List<String> getRegisteredCommands () throws FactoryNullPointerException {
        if(commandsFactory == null) {
            throw new FactoryNullPointerException("");
        }
        return commandsFactory.getRegisteredCommands();
    }
    private static String patterSequence = "[a-zA-Z0-9_\\s]*";
    private static Pattern pattern = Pattern.compile(patterSequence);
    private static Splitter splitter = Splitter.on(' ').trimResults().omitEmptyStrings();
    private ICommandsFactory commandsFactory;
    private Map<String, IReceiver> receivers;
    private Matcher matcher;
}
