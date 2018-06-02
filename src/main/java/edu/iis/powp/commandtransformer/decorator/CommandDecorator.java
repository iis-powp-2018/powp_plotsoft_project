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
     * Field which contains decorated commands
     */
    protected ICompoundCommand compoundCommand;
    /**
     * Field which contains coordinates of current plotter head movement
     */
    protected List<PlotterMovementModel> coordinates;
    /**
     * This field is used to know what command is set in plotter
     */
    protected IPlotterCommand baseCommand;

    /**
     * @param baseCommand command which is set in plotter
     */
    public CommandDecorator(IPlotterCommand baseCommand) {
        InterceptCoordinatesAdapterPlotterDriver plotterDriver = new InterceptCoordinatesAdapterPlotterDriver();
        baseCommand.execute(plotterDriver);
        coordinates = plotterDriver.getCoordinates();
        this.baseCommand = baseCommand;
    }

    /**
     * Method which invoke execute method from compoundCommand field
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
     * Method which creates extended commands
     * @param coordinates list of coordinates of plotter head movement
     * @return ICompoundCommand
     */
    protected abstract ICompoundCommand constructTransformedCompoundCommand(List<PlotterMovementModel> coordinates);
}
