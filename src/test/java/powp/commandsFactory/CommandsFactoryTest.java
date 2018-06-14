package powp.commandsFactory;

import org.junit.jupiter.api.Test;
import powp.CommandsManager.CommandTestSystemOut;

class CommandsFactoryTest {

    @Test
    void exportAndImportFactory() throws powp.commandsFactory.exceptions.IllegalFactoryObjectName {
        CommandsFactory commandsFactory = new CommandsFactory();
        commandsFactory.addCommandToFactory(new CommandTestSystemOut(), "TestCommand");
        commandsFactory.exportFactory("test_factory.txt");

        CommandsFactory commandsFactory2 = new CommandsFactory();
        commandsFactory2.importFactory("test_factory.txt");
        commandsFactory2.getCommand("TestCommand").execute(null, null);
    }
}