package powp.commandManager;

import java.util.HashSet;
import java.util.Set;

public class Receiver implements IReceiver {
    @Override
    public final void addCommand(final String commandName) {
        availableCommandsForObject.add(commandName);
    }

    @Override
    public void deleteCommand(final String commandName) {

        availableCommandsForObject.remove(commandName);
        // Obsłużyć exception ewentualnie.
    }

    @Override
    public final boolean commandIsRegistered(final String command) {

        return availableCommandsForObject.contains(command);
    }

    public Receiver() {
        availableCommandsForObject = new HashSet<>();
    }

    Set<String> availableCommandsForObject;
}
