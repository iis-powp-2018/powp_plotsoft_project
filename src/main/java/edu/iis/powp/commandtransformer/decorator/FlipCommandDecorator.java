package edu.iis.powp.commandtransformer.decorator;

import edu.iis.powp.command.*;
import edu.iis.powp.commandtransformer.command.CompoundCommand;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

import java.util.List;

public class FlipCommandDecorator extends CommandDecorator {
    private boolean flipX;
    private boolean flipY;

    public FlipCommandDecorator(IPlotterCommand baseCommand, boolean flipX, boolean flipY) {
        super(baseCommand);
        this.flipX = flipX;
        this.flipY = flipY;
        compoundCommand = constructTransformedCompoundCommand(coordinates);
    }

    @Override
    protected ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(baseCommand.toString());
        builder.append(" -> flip(");
        if(flipX) {
            builder.append("x");
        }
        if(flipX && flipY) {
            builder.append(", ");
        }
        if(flipY) {
            builder.append("y");
        }
        builder.append(")");

        return builder.toString();
    }
}
