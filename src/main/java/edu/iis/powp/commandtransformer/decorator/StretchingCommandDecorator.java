package edu.iis.powp.commandtransformer.decorator;

import edu.iis.powp.command.*;
import edu.iis.powp.commandtransformer.command.CompoundCommand;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

import java.util.List;

public class StretchingCommandDecorator extends CommandDecorator {

    private boolean stretchX;
    private boolean stretchY;
    private int stretchValue;

    public StretchingCommandDecorator(IPlotterCommand baseCommand, boolean stretchX, boolean stretchY, int stretchValue) {
        super(baseCommand);
        this.stretchX = stretchX;
        this.stretchY = stretchY;
        this.stretchValue = stretchValue;
        compoundCommand = constructTransformedCompoundCommand(coordinates);

    }

    @Override
    protected ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
        CompoundCommand result = new CompoundCommand();
        coordinates.forEach(coordinate -> {
            IPlotterCommand command;
            if(stretchValue > 1) {
                if(stretchX) {
                    coordinate.setPosX(coordinate.getPosX() * stretchValue);
                }
                if(stretchY) {
                    coordinate.setPosY(coordinate.getPosY() * stretchValue);
                }
            } else if (stretchValue < 0) {
                if(stretchX) {
                    coordinate.setPosX(coordinate.getPosX() / (stretchValue * (-1)));
                }

                if(stretchY) {
                    coordinate.setPosY(coordinate.getPosY() / (stretchValue * (-1)));
                }
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
        builder.append(" -> stretch(");
        if(stretchX) {
            builder.append("x");
        }
        if(stretchX && stretchY) {
            builder.append(", ");
        }
        if(stretchY) {
            builder.append("y");
        }
        builder.append(")");

        return builder.toString();
    }
}
