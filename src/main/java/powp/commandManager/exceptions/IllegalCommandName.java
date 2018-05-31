package powp.commandManager.exceptions;

public class IllegalCommandName extends IOException {
    IllegalCommandName(String message) {
        super(message);
    }
}