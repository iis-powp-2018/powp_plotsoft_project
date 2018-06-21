package powp.commandManager.exceptions;

import java.io.IOException;

public class IllegalRegisteredObjectName extends IOException {
    public IllegalRegisteredObjectName(final String message) {
        super(message);
    }
}