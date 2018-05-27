package edu.iis.powp.decorator;

import edu.iis.powp.command.*;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.List;

public class RotationCommandDecorator extends CommandDecorator {
    private int rotationAngle;

    public RotationCommandDecorator(IPlotterCommand baseCommand, int rotationAngle) {
        super(baseCommand);
        this.rotationAngle = rotationAngle;
        compoundCommand = constructTransformedCompoundCommand(coordinates);
    }

    @Override
    protected ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
        CompoundCommand result = new CompoundCommand();
        coordinates.forEach(coordinate -> {
            IPlotterCommand command;
            coordinate.setPosX(getRotatedX(coordinate.getPosX(), coordinate.getPosY()));
            coordinate.setPosY(getRotatedY(coordinate.getPosX(), coordinate.getPosY()));

            if (coordinate.isDrawing()) {
                command = new DrawToCommand(coordinate.getPosX(), coordinate.getPosY());
            } else {
                command = new SetPositionCommand(coordinate.getPosX(), coordinate.getPosY());
            }
            result.addPlotterCommand(command);
        });
        return result;
    }

    private int getRotatedX(int x, int y) {
        double rotationAngleInRadians = Math.toRadians(rotationAngle);
        return (int) (x * Math.cos(rotationAngleInRadians) - y * Math.sin(rotationAngleInRadians));
    }

    private int getRotatedY(int x, int y) {
        double rotationAngleInRadians = Math.toRadians(rotationAngle);
        return (int) (y * Math.cos(rotationAngleInRadians) + x * Math.sin(rotationAngleInRadians));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(baseCommand.toString());
        builder.append(" -> rotation(");
        builder.append(rotationAngle);
        builder.append(")");
        return builder.toString();
    }
}

