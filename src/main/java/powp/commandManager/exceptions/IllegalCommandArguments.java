package powp.commandManager.exceptions;

public class IllegalCommandArguments extends IOException {
    IllegalCommandArguments(String message) {
        super(message);
    }
}