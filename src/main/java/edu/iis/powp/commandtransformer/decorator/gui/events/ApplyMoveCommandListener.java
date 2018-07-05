package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.decorator.MoveCommandDecorator;
import edu.iis.powp.commandtransformer.model.MoveCommandComponentModel;

public class ApplyMoveCommandListener extends ApplyCommandListener<MoveCommandComponentModel> {
    public ApplyMoveCommandListener(MoveCommandComponentModel dataModel) {
        super(dataModel);
    }

    public ApplyMoveCommandListener() {
        this(new MoveCommandComponentModel());
    }

    @Override
    protected void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand) {
        MoveCommandDecorator newCommand = new MoveCommandDecorator(currentCommand, dataModel.getMovementX(), dataModel.getMovementY());
        commandManager.setCurrentCommand(newCommand);
    }
}