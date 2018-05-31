package powp.commandManager.exceptions;

public class IllegalRegisteredObjectName extends IOException {
    IllegalRegisteredObjectName(String message) {
        super(message);
    }
}