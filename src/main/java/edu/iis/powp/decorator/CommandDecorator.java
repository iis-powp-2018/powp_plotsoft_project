package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.InterceptCoordinatesAdapterPlotterDriver;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.List;

public abstract class CommandDecorator implements IPlotterCommand {
    protected ICompoundCommand compoundCommand;
    protected List<PlotterMovementModel> coordinates;
    protected IPlotterCommand baseCommand;

    public CommandDecorator(IPlotterCommand baseCommand) {
        InterceptCoordinatesAdapterPlotterDriver plotterDriver = new InterceptCoordinatesAdapterPlotterDriver();
        baseCommand.execute(plotterDriver);
        coordinates = plotterDriver.getCoordinates();
        this.baseCommand = baseCommand;
    }

    @Override
    public void execute(IPlotter plotter) {
        compoundCommand.execute(plotter);
    }

    protected abstract ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates);
}
