package edu.iis.powp.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import powp.InterfaceAdapter;
import powp.commandManager.ICommand;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class CommandSerializer {
    public void exportCommand(IPlotterCommand command, String path)
    {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(IPlotterCommand.class, new InterfaceAdapter<IPlotterCommand>())
                .create();
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.append(gson.toJson(command));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IPlotterCommand importCommand(String path)
    {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(IPlotterCommand.class, new InterfaceAdapter<IPlotterCommand>())
                .create();

        try (Reader fileReader = new FileReader(path)) {
            return gson.fromJson(fileReader, IPlotterCommand.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
