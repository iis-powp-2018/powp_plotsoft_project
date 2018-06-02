package powp.commandManager;

import powp.commandManager.exceptions.FactoryNullPointerException;
import powp.commandManager.exceptions.IllegalCommandArguments;
import powp.commandManager.exceptions.IllegalCommandName;
import powp.commandManager.exceptions.IllegalRegisteredObjectName;
import powp.commandsFactory.ICommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

public interface ICommandsManager {
    public void sendMessage(final String commandSequence) throws IllegalCommandArguments, IllegalCommandName, IllegalFactoryObjectName, FactoryNullPointerException;
    public void registerObject (IReceiver receiver, final String objectName) throws IllegalRegisteredObjectName;
    public void unregisterObject(IReceiver receiver, final String objectName) throws IllegalCommandArguments;
    public void addObjectToGroup(IReceiver receiver, final String groupName) throws IllegalRegisteredObjectName;
    public void destroyObjectsGroup(final String groupName) throws IllegalRegisteredObjectName;
    public void registerCommandsFactory(ICommandsFactory factory);
}
