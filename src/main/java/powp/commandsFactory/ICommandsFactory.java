package powp.commandsFactory;

import powp.commandManager.ICommand;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;
public interface ICommandsFactory {
    public ICommand getCommand(String key) throws IllegalFactoryObjectName;
    public ICommand cloneCommand(String key) throws IllegalFactoryObjectName;
    public void addCommandToFactory(ICommand command, String key) throws IllegalFactoryObjectName;
    public void deleteObject(String key) throws IllegalFactoryObjectName;
}