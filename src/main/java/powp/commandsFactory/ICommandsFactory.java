package powp.commandsFactory;

import java.util.List;

import edu.iis.powp.command.IPlotterCommand;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;
public interface ICommandsFactory {
    IPlotterCommand getCommand(final String key) throws IllegalFactoryObjectName;
    void addCommandToFactory(IPlotterCommand command, final String key) throws IllegalFactoryObjectName;
    void deleteObject(final String key) throws IllegalFactoryObjectName;
    List<String> getRegisteredCommands();
}