package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.InterceptCoordinatesAdapterPlotterDriver;
import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.List;

public abstract class CommandDecorator implements IPlotterCommand {
    protected CompoundCommand compoundCommand;
    private List<PlotterMovementModel> coordinates;

    public CommandDecorator(IPlotterCommand baseCommand) {
        InterceptCoordinatesAdapterPlotterDriver plotterDriver = new InterceptCoordinatesAdapterPlotterDriver();
        baseCommand.execute(plotterDriver);
        coordinates = plotterDriver.getCoordinates();
    }

    @Override
    public void execute(IPlotter plotter) {
        compoundCommand = constructTransformedCompoundCommand(coordinates);
        compoundCommand.execute(plotter);
    }

    protected abstract CompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates);
}
