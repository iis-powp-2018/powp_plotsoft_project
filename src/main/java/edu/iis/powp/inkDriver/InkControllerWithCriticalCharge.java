package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

import java.util.ArrayList;

public class InkControllerWithCriticalCharge implements IPlotter, InkGuiUpdater, IPlotterCommand {

    private ArrayList<IPlotterCommand> commandList = new ArrayList<>();
    private ArrayList<IPlotterCommand> executedCommandList = new ArrayList<>();

    private InkController plotter;

    public InkControllerWithCriticalCharge(IPlotter plotter, float amountOfInk){
        this.plotter = new InkController(plotter, amountOfInk, true);
    }

    @Override
    public void setPosition(int x, int y) {
        if(plotter.isEnoughInk())
            plotter.setPosition(x,y);
        else
            commandList.add(new SetPositionCommand(x,y));
    }

    @Override
    public void drawTo(int x, int y) {
        if(plotter.isEnoughInk(x,y))
            plotter.drawTo(x,y);
        else
            commandList.add(new DrawToCommand(x,y));
    }

    @Override
    public void execute(IPlotter driver) {
        for(IPlotterCommand command : commandList){
            command.execute(driver);
            executedCommandList.add(command);
            if(!plotter.isEnoughInk()) {
                executedCommandList.remove(command);
                break;
            }
        }
        commandList.removeAll(executedCommandList);
        executedCommandList.clear();
    }

    @Override
    public void updateValue(float value) {
        plotter.updateValue(value);
        this.execute(plotter);
    }
}
