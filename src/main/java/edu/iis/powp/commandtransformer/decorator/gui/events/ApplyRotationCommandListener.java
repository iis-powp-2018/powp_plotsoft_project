package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.decorator.RotationCommandDecorator;
import edu.iis.powp.commandtransformer.model.RotationCommandComponentModel;

public class ApplyRotationCommandListener extends ApplyCommandListener<RotationCommandComponentModel> {
    public ApplyRotationCommandListener(RotationCommandComponentModel dataModel) {
        super(dataModel);
    }

    public ApplyRotationCommandListener() {
        this(new RotationCommandComponentModel());
    }

    @Override
    protected void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand) {
        RotationCommandDecorator newCommand = new RotationCommandDecorator(currentCommand, dataModel.getRotation());
        commandManager.setCurrentCommand(newCommand);
    }
}