package edu.iis.powp.commandtransformer.decorator;

import edu.iis.powp.command.*;
import edu.iis.powp.commandtransformer.command.CompoundCommand;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

import java.util.List;

public class GraduationCommandDecorator extends CommandDecorator {

    private Integer graduationValue;

    public GraduationCommandDecorator(IPlotterCommand baseCommand, Integer graduationValue) {
        super(baseCommand);
        this.graduationValue = graduationValue;
        compoundCommand = constructTransformedCompoundCommand(coordinates);

    }

    @Override
    protected ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates) {
        CompoundCommand result = new CompoundCommand();
        coordinates.forEach(coordinate -> {
            IPlotterCommand command;
                if(graduationValue > 1) {
                    coordinate.setPosX(coordinate.getPosX() * graduationValue);
                    coordinate.setPosY(coordinate.getPosY() * graduationValue);
                } else if (graduationValue < 0) {
                    coordinate.setPosX(coordinate.getPosX() / (graduationValue * (-1)));
                    coordinate.setPosY(coordinate.getPosY() / (graduationValue * (-1)));
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
        return baseCommand.toString() + " -> graduation(" + graduationValue + ")";
    }
}
