package powp.commandsFactory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import powp.CommandsManager.CommandTestSystemOut;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CommandsFactoryTest {

    @Test
    void exportAndImportFactory() throws powp.commandsFactory.exceptions.IllegalFactoryObjectName {
        CommandsFactory commandsFactory = new CommandsFactory();
        commandsFactory.addCommandToFactory(new CommandTestSystemOut("Test message"), "TestCommand");
        commandsFactory.exportFactory("test_factory.json");
        Assert.assertEquals(true, Files.exists(Paths.get(("test_factory.json"))));

        CommandsFactory commandsFactory2 = new CommandsFactory();
        commandsFactory2.importFactory("test_factory.json");
        CommandTestSystemOut command = (CommandTestSystemOut)commandsFactory2.getCommand("TestCommand");
        command.execute(null, null);
        Assert.assertEquals("Test message", command.getMessage());

    }
}