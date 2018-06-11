package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.decorator.GraduationCommandDecorator;
import edu.iis.powp.commandtransformer.model.GraduationCommandComponentModel;

public class ApplyGraduationCommandListener extends ApplyCommandListener<GraduationCommandComponentModel> {
    public ApplyGraduationCommandListener(GraduationCommandComponentModel dataModel) {
        super(dataModel);
    }

    public ApplyGraduationCommandListener() {
        this(new GraduationCommandComponentModel());
    }

    @Override
    protected void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand) {
        GraduationCommandDecorator newCommand = new GraduationCommandDecorator(currentCommand, dataModel.getGraduation());
        commandManager.setCurrentCommand(newCommand);
    }
}