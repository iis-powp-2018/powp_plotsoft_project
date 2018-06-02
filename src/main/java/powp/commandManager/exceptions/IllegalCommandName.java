package powp.commandManager.exceptions;

import java.io.IOException;

public class IllegalCommandName extends IOException {
    public IllegalCommandName(final String message) {
        super(message);
    }
}