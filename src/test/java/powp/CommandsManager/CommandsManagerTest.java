package powp.CommandsManager;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.IPlotterCommand;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import powp.commandManager.CommandsManager;
import powp.commandManager.exceptions.IllegalCommandArguments;
import powp.commandManager.exceptions.IllegalCommandName;
import powp.commandManager.exceptions.IllegalRegisteredObjectName;
import powp.commandsFactory.CommandsFactory;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CommandsManagerTest {

    final IPlotterCommand command = new CommandTestSystemOut();

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
        terminal.registerObject(new ExamplePlotter(),"exampleObjectName");

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

        terminal.registerObject(new ExamplePlotter(),"ExampleObjectName");
        Throwable thrown = catchThrowable(() -> { terminal.registerObject(new ExamplePlotter(),"ExampleObjectName");});

        // when
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"$","//  //", "##"})
    public void shouldThrowWhenTextPatternIsWrong(String wrongPattern) {

        CommandsManager terminal = new CommandsManager();
        Throwable thrown;
        IPlotter receiver = new ExamplePlotter();

        thrown = catchThrowable(() -> { terminal.registerObject(receiver, null);});
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(Exception.class);

        thrown = catchThrowable(() -> { terminal.registerObject(receiver, wrongPattern);});
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(Exception.class);
    }

    public void shouldThrowExceptionWhenObjectIsUnregistered() throws IllegalRegisteredObjectName {
        CommandsManager terminal = new CommandsManager();
        IPlotter receiver = new ExamplePlotter();
        terminal.registerObject(new ExamplePlotter(),"ObjectName");
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
        IPlotter receiver = new ExamplePlotter();
        terminal.registerObject(receiver,"ExampleObjectName");
        terminal.unregisterObject(receiver,"ExampleObjectName");
    }
}