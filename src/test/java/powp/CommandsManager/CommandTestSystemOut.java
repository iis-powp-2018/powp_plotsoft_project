package powp.CommandsManager;

import powp.commandManager.ICommand;

public class CommandTestSystemOut implements ICommand {

    private String message = "It's work.";

    @Override
    public void execute(Object otherObject, String[] arguments) {
        System.out.print(message);
    }

    @Override
    public ICommand clone() {
        return null;
    }

    public String getMessage() {
        return message;
    }

    public CommandTestSystemOut() {
    }

    public CommandTestSystemOut(String message) {
        this.message = message;
    }
}
