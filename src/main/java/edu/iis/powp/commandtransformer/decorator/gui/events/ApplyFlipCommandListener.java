package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.decorator.FlipCommandDecorator;
import edu.iis.powp.commandtransformer.model.FlipCommandComponentModel;

public class ApplyFlipCommandListener extends ApplyCommandListener<FlipCommandComponentModel> {
    public ApplyFlipCommandListener(FlipCommandComponentModel dataModel) {
        super(dataModel);
    }

    public ApplyFlipCommandListener() {
        this(new FlipCommandComponentModel());
    }

    @Override
    protected void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand) {
        FlipCommandDecorator newCommand = new FlipCommandDecorator(currentCommand, dataModel.getFlipX(), dataModel.getFlipY());
        commandManager.setCurrentCommand(newCommand);
    }
}
