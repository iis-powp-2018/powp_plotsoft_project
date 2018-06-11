package edu.iis.powp.commandtransformer.decorator.gui.events;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandtransformer.model.CommandTransformerCreatorComponentModel;
import edu.iis.powp.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ApplyCommandListener<T extends CommandTransformerCreatorComponentModel> implements ActionListener {
    protected T dataModel;

    public ApplyCommandListener(T dataModel) {
        this.dataModel = dataModel;
    }

    public T getDataModel() {
        return dataModel;
    }

    public void setDataModel(T dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlotterCommandManager commandManager = CommandsFeature.getPlotterCommandManager();
        IPlotterCommand currentCommand = commandManager.getCurrentCommand();
        if (currentCommand != null) {
            applyCommand(commandManager, currentCommand);
        }
    }

    protected abstract void applyCommand(PlotterCommandManager commandManager, IPlotterCommand currentCommand);
}
