package edu.iis.powp.decorator;

import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.List;

public class MoveCommandDecorator extends CommandDecorator {
    private int moveX;
    private int moveY;

    public MoveCommandDecorator(IPlotterCommand baseCommand, int moveX, int moveY) {
        super(baseCommand);
        this.moveX = moveX;
        this.moveY = moveY;
    }

    @Override
    protected CompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
        CompoundCommand result = new CompoundCommand();
        coordinates.forEach(coordinate -> {
            IPlotterCommand command;
            coordinate.setPosX(coordinate.getPosX() + moveX);
            coordinate.setPosY(coordinate.getPosY() + moveY);
            if(coordinate.isDrawing()) {
                command = new DrawToCommand(coordinate.getPosX(), coordinate.getPosY());
            } else {
                command = new SetPositionCommand(coordinate.getPosX(), coordinate.getPosY());
            }
            result.addPlotterCommand(command);
        });
        return result;
    }
}
