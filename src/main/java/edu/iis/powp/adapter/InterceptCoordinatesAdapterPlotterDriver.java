package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Adapter which is responsible for storage list of specific command's coordinates
 */
public class InterceptCoordinatesAdapterPlotterDriver implements IPlotter {
    /**
     * Field which stores list of coordinates
     */
    private List<PlotterMovementModel> coordinates;

    public InterceptCoordinatesAdapterPlotterDriver() {
        coordinates = new LinkedList<>();
    }

    /**
     * Method which is use to add new PlotterMovementModel to the list with current coordinates
     * @param i present first coordinate
     * @param i1 present second coordinate
     */
    @Override
    public void setPosition(int i, int i1) {
        coordinates.add(new PlotterMovementModel(i, i1, false));
    }

    /**
     * Method which is use to add new PlotterMovementModel to the list with current coordinates
     * @param i present first coordinate
     * @param i1 present second coordinate
     */
    @Override
    public void drawTo(int i, int i1) {
        coordinates.add(new PlotterMovementModel(i, i1, true));
    }

    /**
     * Getter for the list
     * @return list of coordinates
     */
    public List<PlotterMovementModel> getCoordinates() {
        return new LinkedList<>(coordinates);
    }
}
