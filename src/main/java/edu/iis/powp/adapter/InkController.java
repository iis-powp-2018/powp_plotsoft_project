package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.observer.Subscriber;

import static edu.iis.powp.adapter.InkController.operationType.drawpos;
import static edu.iis.powp.adapter.InkController.operationType.setpos;

public class InkController implements IPlotter{

    IPlotter plotter;

    public enum operationType{
        setpos,
        drawpos
    }
    operationType opType;

    float amountOfInk;
    double posX, posY;

    public InkController(IPlotter plotter, float amountOfInk){
        this.plotter = plotter;
        this.amountOfInk = amountOfInk;
    }

    @Override
    public void setPosition(int x, int y) {
        plotter.setPosition(x, y);

        posX = x;
        posY = y;
        opType = setpos;
        System.out.println("Int controller adapter - " + opType);
    }

    @Override
    public void drawTo(int x, int y) {

        plotter.drawTo(x, y);
        opType = drawpos;
        System.out.println("Ink controller adapter - " + opType);
        if(opType == drawpos)
        {
            amountOfInk -= Math.sqrt(Math.pow((posX - x), 2) + Math.pow(posY - y, 2));
        }
        posX = x;
        posY = y;
        System.out.println("Amount of ink - " + amountOfInk);
    }

}