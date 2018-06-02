package powp.CommandsManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import powp.commandManager.CommandsManager;
import powp.commandManager.ICommand;
import powp.commandManager.IReceiver;
import powp.commandManager.exceptions.IllegalCommandArguments;
import powp.commandManager.exceptions.IllegalCommandName;
import powp.commandManager.exceptions.IllegalRegisteredObjectName;
import powp.commandsFactory.CommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CommandsManagerTest {

    final ICommand command = new CommandTestSystemOut();


    @Test
    public void shouldThrowExceptionWrongObjectName() throws IllegalRegisteredObjectName, IllegalFactoryObjectName {
        CommandsManager terminal = new CommandsManager();
        CommandsFactory factory = new CommandsFactory();
        terminal.registerCommandsFactory(factory);

        Throwable thrown = catchThrowable(() -> { terminal.sendMessage("wrongObjectName ExampleArgument");});
        // when
        assertThat(thrown).isInstanceOf(IllegalCommandName.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"exampleObjectName SystemOut"})
    public void shouldExecuteMethod(String commandSequence) throws IllegalRegisteredObjectName, IllegalFactoryObjectName, IllegalCommandName, IllegalCommandArguments {
        CommandsManager terminal = new CommandsManager();
        CommandsFactory factory = new CommandsFactory();

        terminal.registerCommandsFactory(factory);
        factory.addCommandToFactory(command, "SystemOut");
        terminal.registerObject(new ExampleReceiver(),"exampleObjectName");

        terminal.sendMessage(commandSequence);

        // when
    }

    @ParameterizedTest
    @ValueSource(strings = {"WrongObjectName","WrongObjectName WrongMethod","GoodObjectName WrongMethod"})
    public void shouldThrowExceptionWrongCommand(String commandSequence) throws IllegalRegisteredObjectName, IllegalFactoryObjectName {
        CommandsManager terminal = new CommandsManager();
        CommandsFactory factory = new CommandsFactory();
        terminal.registerCommandsFactory(factory);
        factory.addCommandToFactory(command,"GoodObjectName");

        Throwable thrown = catchThrowable(() -> { terminal.sendMessage(commandSequence);});

        // when
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    public void shouldThrowExceptionWhenRedefinitionObjectName() throws IllegalRegisteredObjectName, IllegalFactoryObjectName {
        CommandsManager terminal = new CommandsManager();

        terminal.registerObject(new ExampleReceiver(),"ExampleObjectName");
        Throwable thrown = catchThrowable(() -> { terminal.registerObject(new ExampleReceiver(),"ExampleObjectName");});

        // when
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    public void shouldThrowExceptionWhenObjectIsUnregistered() throws IllegalRegisteredObjectName {

        CommandsManager terminal = new CommandsManager();
        IReceiver receiver = new ExampleReceiver();
        terminal.registerObject(new ExampleReceiver(),"ObjectName");
        Throwable thrown = catchThrowable(() -> { terminal.unregisterObject(receiver, "WrongCommandName");});
        // when
        assertThat(thrown).isInstanceOf(Exception.class);

        thrown = catchThrowable(() -> { terminal.unregisterObject(null, "WrongCommandName");});
        assertThat(thrown).isInstanceOf(Exception.class);

        thrown = catchThrowable(() -> { terminal.unregisterObject(receiver, null);});
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    public void shouldUnregisterObjectAndRegisterAgain() throws IllegalRegisteredObjectName, IllegalCommandArguments {

        CommandsManager terminal = new CommandsManager();
        IReceiver receiver = new ExampleReceiver();
        terminal.registerObject(receiver,"ExampleObjectName");
        terminal.unregisterObject(receiver,"ExampleObjectName");
    }
}
