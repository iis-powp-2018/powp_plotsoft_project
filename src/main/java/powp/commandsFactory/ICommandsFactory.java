package powp.commandsFactory;

public interface ICommandsFactory {
    public ICommand getCommand(String key) throw IllegalFactoryObjectName;
    public ICommand cloneCommand(String key) throw IllegalFactoryObjectName;
    public void addCommandToFactory(ICommand command, String key) throw IllegalFactoryObjectName;
    public void deleteObject(String key) throw IllegalFactoryObjectName;
}
