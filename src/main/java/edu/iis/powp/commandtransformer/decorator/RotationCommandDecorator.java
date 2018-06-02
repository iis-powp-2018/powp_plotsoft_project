package edu.iis.powp.commandtransformer.decorator;

import edu.iis.powp.command.*;
import edu.iis.powp.commandtransformer.command.CompoundCommand;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

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
            setRotatedCoordinates(coordinate);
            if (coordinate.isDrawing()) {
                command = new DrawToCommand(coordinate.getPosX(), coordinate.getPosY());
            } else {
                command = new SetPositionCommand(coordinate.getPosX(), coordinate.getPosY());
            }
            result.addPlotterCommand(command);
        });
        return result;
    }

    private void setRotatedCoordinates(PlotterMovementModel coordinate){
        double rotationAngleInRadians = Math.toRadians(rotationAngle);
        double sin = Math.sin(rotationAngleInRadians);
        double cos = Math.cos(rotationAngleInRadians);

        double rotatedXPoint = (coordinate.getPosX() * cos) - (coordinate.getPosY() * sin);
        double rotatedYPoint = (coordinate.getPosX() * sin) + (coordinate.getPosY() * cos);

        coordinate.setPosY((int)rotatedYPoint);
        coordinate.setPosX((int)rotatedXPoint);
    }

    @Override
    public String toString() {
        return baseCommand.toString() + " -> rotation(" + rotationAngle + ")";
    }
}

