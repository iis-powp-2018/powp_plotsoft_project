package powp.commandsFactory.exceptions;

import java.io.IOException;

public class IllegalFactoryObjectName extends IOException {
    public IllegalFactoryObjectName(String message) {
        super(message);
    }
}