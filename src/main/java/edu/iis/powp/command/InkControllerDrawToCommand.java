package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;


public class InkControllerDrawToCommand implements InkControllerCommand{

    private int x,y;

    public InkControllerDrawToCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(IPlotter driver) {
        driver.drawTo(this.x, this.y);
    }

}