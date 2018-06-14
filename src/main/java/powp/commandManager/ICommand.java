package powp.commandManager;

public interface ICommand {
    public void execute(Object otherObject, String [] arguments);
    public ICommand clone();
}
