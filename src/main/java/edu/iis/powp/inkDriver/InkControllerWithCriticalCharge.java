package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

import java.util.ArrayList;

public class InkControllerWithCriticalCharge implements IPlotter, IController, IPlotterCommand {

    private ArrayList<IPlotterCommand> commandList = new ArrayList<>();
    private ArrayList<IPlotterCommand> executedCommandList = new ArrayList<>();

    private InkController inkController;
    private InkGuiLogic inkGuiLogic;

    public InkControllerWithCriticalCharge(IPlotter plotter, float amountOfInk, InkGuiLogic inkGuiLogic){
        this.inkGuiLogic = inkGuiLogic;
        this.inkController = new InkController(plotter, amountOfInk, inkGuiLogic,true);
    }

    @Override
    public void setPosition(int x, int y) {
        if(inkController.isEnoughInk())
            inkController.setPosition(x,y);
        else
            commandList.add(new SetPositionCommand(x,y));
    }

    @Override
    public void drawTo(int x, int y) {
        if(inkController.isEnoughInkForDrawing(x,y))
            inkController.drawTo(x,y);
        else {
            commandList.add(new DrawToCommand(x, y));
            inkGuiLogic.informationPopUp();
        }
    }

    @Override
    public void execute(IPlotter driver) {
        for(IPlotterCommand command : commandList){
            command.execute(driver);
            executedCommandList.add(command);
            if(!inkController.isEnoughInk()) {
                inkGuiLogic.informationPopUp();
                executedCommandList.remove(command);
                break;
            }
        }
        commandList.removeAll(executedCommandList);
        executedCommandList.clear();
    }

    @Override
    public void updateValueInController(float value) {
        inkController.updateValueInController(value);
        this.execute(inkController);
    }
}
