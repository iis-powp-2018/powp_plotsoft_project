package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandSerializerTest {

    private class CompoundCommandTest implements ICompoundCommand {
        private List<IPlotterCommand> commands = new ArrayList<>();

        public CompoundCommandTest() {
            commands.add(new DrawToCommand(0, 1));
            commands.add(new DrawToCommand(1, 2));
            commands.add(new DrawToCommand(2, 3));
            commands.add(new DrawToCommand(4, 5));
        }

        @Override
        public Iterator<IPlotterCommand> iterator() {
            return commands.iterator();
        }

        @Override
        public void execute(IPlotter plotter) {
            for (IPlotterCommand command : commands) {
                command.execute(plotter);
            }
        }
    }

    @Test
    void MainTest()
    {
        IPlotterCommand exportingCommand = new DrawToCommand(5, 12);
        CommandSerializer commandSerializer = new CommandSerializer();
        commandSerializer.exportCommand(exportingCommand, "command.json");
        Assert.assertEquals(true, Files.exists(Paths.get(("command.json"))));

        IPlotterCommand importingCommand = commandSerializer.importCommand("command.json");
        importingCommand.execute(new IPlotter() {
            @Override
            public void setPosition(int i, int i1) {
            }

            @Override
            public void drawTo(int x, int y) {
                Assert.assertEquals(5, x);
                Assert.assertEquals(12, y);
            }
        });
    }


    @Test
    void CompoundCommandTest()
    {
        IPlotterCommand compoundCommand = new CompoundCommandTest();
        CommandSerializer commandSerializer = new CommandSerializer();
        commandSerializer.exportCommand(compoundCommand, "compound_command.json");
        Assert.assertEquals(true, Files.exists(Paths.get(("compound_command.json"))));

        IPlotterCommand importingCommand = commandSerializer.importCommand("compound_command.json");
        importingCommand.execute(new IPlotter() {
            @Override
            public void setPosition(int i, int i1) {
            }

            @Override
            public void drawTo(int x, int y) {
                Assert.assertEquals(x+1, y);
            }
        });
    }
}