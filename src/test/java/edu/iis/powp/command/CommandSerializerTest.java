package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CommandSerializerTest {

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

}