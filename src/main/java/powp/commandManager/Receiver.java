package powp.commandManager;

import powp.commandManager.exceptions.IllegalCommandArguments;

import java.util.HashSet;
import java.util.Set;

public class Receiver implements IReceiver {
    @Override
    public final void addCommand(final String commandName) {
        availableCommandsForObject.add(commandName);
    }

    @Override
    public void deleteCommand(final String commandName) {
        availableCommandsForObject.remove(commandName);
        // Obsłużyć exception ewentualnie.
    }

    @Override
    public final boolean commandIsRegistered(final String command) {

        return availableCommandsForObject.contains(command);
    }

    @Override
    public void finalize() {
        if(commandsManagerHandle != null && objectName != null) {
            try {
                    commandsManagerHandle.unregisterObject(this, objectName);
                }
            catch (IllegalCommandArguments illegalCommandArguments) {
            }
        }
    }

    public Receiver() {
        commandsManagerHandle = null;
        availableCommandsForObject = new HashSet<>();
        objectName = null;
    }

    public Receiver(ICommandsManager commandsManager, String objectName) {
        commandsManagerHandle = commandsManager;
        this.objectName = objectName;
    }

    @Override
    public final void  setTerminalHandle(ICommandsManager commandsManager) {
        commandsManagerHandle = commandsManager;
    }

    public final void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    private ICommandsManager commandsManagerHandle;
    private Set<String> availableCommandsForObject;
    private String objectName;

}
