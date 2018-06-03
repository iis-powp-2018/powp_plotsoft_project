package edu.iis.powp.commandtransformer.decorator;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.commandtransformer.adapter.InterceptCoordinatesAdapterPlotterDriver;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

import java.util.Iterator;
import java.util.List;

/**
 * The CommandDecorator specifies common behaviour for classes which extends action of IPlotterCommand
 */
public abstract class CommandDecorator implements ICompoundCommand {
    /**
     * Result of decoration
     */
    protected ICompoundCommand compoundCommand;
    /**
     * Field which contains coordinates of current plotter head movement
     */
    protected List<PlotterMovementModel> coordinates;
    /**
     * Command that will be decorated
     */
    protected IPlotterCommand baseCommand;

    /**
     * @param baseCommand command to decorate
     */
    public CommandDecorator(IPlotterCommand baseCommand) {
        InterceptCoordinatesAdapterPlotterDriver plotterDriver = new InterceptCoordinatesAdapterPlotterDriver();
        baseCommand.execute(plotterDriver);
        coordinates = plotterDriver.getCoordinates();
        this.baseCommand = baseCommand;
    }

    /**
     * Execute decorated command
     * @param plotter the plotter which is used to draw figure
     */
    @Override
    public void execute(IPlotter plotter) {
        compoundCommand.execute(plotter);
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return compoundCommand.iterator();
    }

    /**
     * Method which constructs decorated ICompoundCommand from given base command coordinates
     * @param coordinates list of coordinates taken from base command
     * @return ICompoundCommand decorated compound command
     */
    protected abstract ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates);
}
