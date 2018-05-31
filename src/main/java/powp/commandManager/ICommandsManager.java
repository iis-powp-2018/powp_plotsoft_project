package powp.commandManager;

public interface ICommandsManager {
    public void sendMessage(String commandSequence);
    public void registerObject (ICommand command, String objectName) throw IllegalRegisteredObjectName;
    public void unregisterObject(ICommand command, String objectName) throw IllegalRegisteredObjectName;
    public void addObjectToGroup(ICommand command, String groupName) throw IllegalRegisteredObjectName;
    public void destroyObjectsGroup(String groupName) throw IllegalRegisteredObjectName;
    public void registerCommandsFactory(ICommandsFactory factory);
}
