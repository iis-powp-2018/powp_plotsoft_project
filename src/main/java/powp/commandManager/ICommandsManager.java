package powp.commandManager;

import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import powp.commandManager.exceptions.FactoryNullPointerException;
import powp.commandManager.exceptions.IllegalCommandArguments;
import powp.commandManager.exceptions.IllegalCommandName;
import powp.commandManager.exceptions.IllegalRegisteredObjectName;
import powp.commandsFactory.ICommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

public interface ICommandsManager {
    void sendMessage(final String commandSequence) throws IllegalCommandArguments, IllegalCommandName, IllegalFactoryObjectName, FactoryNullPointerException;
    void registerObject (IPlotter receiver, final String objectName) throws IllegalRegisteredObjectName;
    void unregisterObject(IPlotter receiver, final String objectName) throws IllegalCommandArguments;
    void registerCommandsFactory(ICommandsFactory factory);
    List<String> getRegisteredCommands() throws FactoryNullPointerException;

}
