package powp.commandManager;

public interface IReceiver {
    public boolean commandIsRegistered(final String commandName);
    public void addCommand(final String commandName);
    public void deleteCommand(final String commandName);
}
