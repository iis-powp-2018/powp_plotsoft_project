package edu.iis.powp.decorator;

import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.List;

public class FlipCommandDecorator extends CommandDecorator {
    private boolean flipX;
    private boolean flipY;

    public FlipCommandDecorator(IPlotterCommand baseCommand, boolean flipX, boolean flipY) {
        super(baseCommand);
        this.flipX = flipX;
        this.flipY = flipY;
    }


    @Override
    protected CompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
        CompoundCommand result = new CompoundCommand();
        coordinates.forEach(coordinate -> {
            IPlotterCommand command;
            if(flipX) {
                coordinate.setPosX(coordinate.getPosX() * -1);
            }
            if(flipY) {
                coordinate.setPosY(coordinate.getPosY() * -1);
            }
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
