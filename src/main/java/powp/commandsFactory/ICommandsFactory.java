package powp.commandsFactory;

import java.util.List;
import powp.commandManager.ICommand;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;
public interface ICommandsFactory {
    public ICommand getCommand(final String key) throws IllegalFactoryObjectName;
    public ICommand cloneCommand(final String key) throws IllegalFactoryObjectName;
    public void addCommandToFactory(ICommand command, final String key) throws IllegalFactoryObjectName;
    public void deleteObject(final String key) throws IllegalFactoryObjectName;
    public List<String> getRegisteredCommands();
}