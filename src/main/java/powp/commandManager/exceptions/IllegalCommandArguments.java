package powp.commandManager.exceptions;

import java.io.IOException;

public class IllegalCommandArguments extends IOException {
    public IllegalCommandArguments(final String message) {
        super(message);
    }
}