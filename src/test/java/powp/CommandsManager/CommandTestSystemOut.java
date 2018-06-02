package powp.CommandsManager;

import powp.commandManager.ICommand;

public class CommandTestSystemOut implements ICommand {
    @Override
    public void execute(Object otherObject, String[] arguments) {
        System.out.print("It's work.");
    }

    @Override
    public ICommand clone() {
        return null;
    }
}
