package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.InkController;
import edu.iis.powp.adapter.InkGuiUpdater;

import java.util.ArrayList;

public class InkControllerComplexCommand implements IPlotter, InkGuiUpdater, InkControllerCommand {

    private ArrayList<InkControllerCommand> commandList = new ArrayList<>();
    private int executedCommands = 0;

    private InkController plotter;

    public InkControllerComplexCommand(IPlotter plotter){
        this.plotter = (InkController) plotter;
    }

    @Override
    public void setPosition(int i, int i1) {
        if(plotter.isEnoughInk())
            plotter.setPosition(i,i1);
        else
            commandList.add(new InkControllerSetPositionCommand(i,i1));
    }

    @Override
    public void drawTo(int i, int i1) {
        if(plotter.isEnoughInk())
            plotter.drawTo(i,i1);
        else
            commandList.add(new InkControllerDrawToCommand(i,i1));
    }

    @Override
    public void execute(IPlotter driver) {
        for(int i = executedCommands; i < commandList.size(); i++){
            if(plotter.isEnoughInk()){
                commandList.get(i).execute(driver);
                executedCommands++;
            }else
                break;
        }
        if(executedCommands == commandList.size()){
            commandList.clear();
            executedCommands=0;
        }
    }

    @Override
    public void updateValue(float value) {
        plotter.updateValue(value);
        this.execute(plotter);
    }
}
