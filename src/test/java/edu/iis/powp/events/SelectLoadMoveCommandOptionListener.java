package edu.iis.powp.events;

import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.decorator.MoveCommandDecorator;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLoadMoveCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
/*        List<IPlotterCommand> commands = new ArrayList<IPlotterCommand>();
        commands.add(new SetPositionCommand(-20, -50));
        commands.add(new DrawToCommand(-20, -50));
        commands.add(new SetPositionCommand(-20, -40));
        commands.add(new DrawToCommand(-20, 50));
        commands.add(new SetPositionCommand(0, -50));
        commands.add(new DrawToCommand(0, -50));
        commands.add(new SetPositionCommand(0, -40));
        commands.add(new DrawToCommand(0, 50));
        commands.add(new SetPositionCommand(70, -50));
        commands.add(new DrawToCommand(20, -50));
        commands.add(new DrawToCommand(20, 0));
        commands.add(new DrawToCommand(70, 0));
        commands.add(new DrawToCommand(70, 50));
        commands.add(new DrawToCommand(20, 50));

        CompoundCommand firstDrawable = new CompoundCommand();
        firstDrawable.addPlotterCommands(commands);*/


        PlotterCommandManager manager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = manager.getCurrentCommand();
        if(currentCommand != null) {
            MoveCommandDecorator decorator = new MoveCommandDecorator(currentCommand, 10, 20);
            manager.setCurrentCommand(decorator);
        }

    }
}
