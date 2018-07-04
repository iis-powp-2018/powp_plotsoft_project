package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.kis.powp.drawer.shape.ILine;
import edu.kis.powp.drawer.shape.LineFactory;


public class InkController implements IPlotter, IController {

    private IPlotter plotter;
    private IGuiLogic iGuiLogic;
    private ILine defaultLine;

    private float amountOfInk, tempAmountOfInk;
    private double posX, posY;
    private boolean enoughInk = true;
    private boolean isCriticalCharged = false;

    @Override
    public void updateValueInController(float value) {
        amountOfInk = value;
        tempAmountOfInk = value;
        iGuiLogic.updateValueInGui(amountOfInk);
        enoughInk = true;
    }

    public InkController(IPlotter plotter, float amountOfInk, IGuiLogic iGuiLogic){
        this.plotter = plotter;
        this.amountOfInk = amountOfInk;
        this.tempAmountOfInk = amountOfInk;
        defaultLine = ((LineAdapterPlotterDriver)plotter).getLine();
        this.iGuiLogic = iGuiLogic;
    }

    public InkController(IPlotter plotter, float amountOfInk, IGuiLogic iGuiLogic, boolean forceStopAndAlert){
        this.plotter = plotter;
        this.amountOfInk = amountOfInk;
        this.tempAmountOfInk = amountOfInk;
        this.iGuiLogic = iGuiLogic;
        this.isCriticalCharged = forceStopAndAlert;
    }

    @Override
    public void setPosition(int x, int y){
        plotter.setPosition(x, y);

        posX = x;
        posY = y;

    }

    @Override
    public void drawTo(int x, int y){
        tempAmountOfInk -= Math.sqrt(Math.pow((posX - x), 2) + Math.pow(posY - y, 2));

        posX = x;
        posY = y;

        if(isCriticalCharged)
            drawWithCriticalCharge(x,y);
        else
            drawWithoutCriticalCharge(x,y);

        iGuiLogic.updateValueInGui(amountOfInk);
    }

    
    public boolean isEnoughInk(){
        return enoughInk;
    }

    public boolean isEnoughInkForDrawing(int x, int y){
        if((tempAmountOfInk - Math.sqrt(Math.pow((posX - x), 2) + Math.pow(posY - y, 2)))<0){
            enoughInk = false;
        }
        return enoughInk;
    }


    public void drawWithCriticalCharge(int x, int y){
        if (tempAmountOfInk > 0)
        {
            amountOfInk = tempAmountOfInk;
            plotter.drawTo(x, y);
        }
        else
        {
            enoughInk = false;
        }
    }

    public void drawWithoutCriticalCharge(int x, int y){
        if (tempAmountOfInk > 0)
        {
            ((LineAdapterPlotterDriver)plotter).setLine(defaultLine);
            amountOfInk = tempAmountOfInk;
            plotter.drawTo(x, y);
        }
        else
        {
            ((LineAdapterPlotterDriver)plotter).setLine(LineFactory.getDottedLine());
            plotter.drawTo(x, y);
        }
    }

}