package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

public class InkControllerSetPositionCommand implements InkControllerCommand {
    private int x,y;

    public InkControllerSetPositionCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(IPlotter driver) {
        driver.setPosition(this.x, this.y);
    }
}
