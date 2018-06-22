package edu.iis.powp.commandtransformer.model;

/**
 * Model used to recreate plotter movement from ICompoundCommand object using 
 * @see edu.iis.powp.commandtransformer.adapter.InterceptCoordinatesAdapterPlotterDriver
 */
public class PlotterMovementModel {
    private int posX;
    private int posY;
    private boolean isDrawing;

    public PlotterMovementModel(int posX, int posY, boolean isDrawing) {
        this.posX = posX;
        this.posY = posY;
        this.isDrawing = isDrawing;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isDrawing() {
        return isDrawing;
    }
}
