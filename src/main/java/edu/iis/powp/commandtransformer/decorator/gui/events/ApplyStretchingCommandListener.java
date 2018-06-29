package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.decorator.StretchingCommandDecorator;
import edu.iis.powp.commandtransformer.model.StretchingCommandComponentModel;

public class ApplyStretchingCommandListener extends ApplyCommandListener<StretchingCommandComponentModel> {
    public ApplyStretchingCommandListener(StretchingCommandComponentModel dataModel) {
        super(dataModel);
    }

    public ApplyStretchingCommandListener() {
        this(new StretchingCommandComponentModel());
    }

    @Override
    protected void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand) {
        StretchingCommandDecorator newCommand = new StretchingCommandDecorator(currentCommand, dataModel.getStretchX(), dataModel.getStretchY(), dataModel.getStretchValue());
        commandManager.setCurrentCommand(newCommand);
    }
}