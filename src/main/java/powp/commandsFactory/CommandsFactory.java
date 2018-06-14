package powp.commandsFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import powp.commandManager.ICommand;
import powp.InterfaceAdapter;
import powp.commandsFactory.exceptions.IllegalFactoryObjectName;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CommandsFactory implements  ICommandsFactory {
    @Override
    public ICommand getCommand(final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if (candidate == null) {
            throw new IllegalFactoryObjectName("");
        }
        return candidate;
    }

    @Override
    public ICommand cloneCommand(final String key) throws IllegalFactoryObjectName {
        return null;
    }

    @Override
    public void addCommandToFactory(ICommand command, final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if (candidate != null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.put(key, command);
        }
    }

    @Override
    public void deleteObject(final String key) throws IllegalFactoryObjectName {
        ICommand candidate = commandsCollection.get(key);
        if(candidate == null) {
            throw new IllegalFactoryObjectName("");
        } else {
            commandsCollection.remove(candidate);
            // Sprawdzić czy zadziała w ogóle.
        }

    }

    public CommandsFactory() {
        commandsCollection = new HashMap<>();
    }

    public void exportFactory(String filePath)
    {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(ICommand.class, new InterfaceAdapter<ICommand>())
                .create();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.append(gson.toJson(commandsCollection));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importFactory(String filePath)
    {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(ICommand.class, new InterfaceAdapter<ICommand>())
                .create();

        try (Reader fileReader = new FileReader(filePath)) {
            Type type = new TypeToken< Map<String, ICommand>>(){}.getType();
            commandsCollection = gson.fromJson(fileReader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, ICommand> commandsCollection;
}
