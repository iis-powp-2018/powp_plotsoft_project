package edu.iis.powp.commandtransformer.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.commandtransformer.model.PlotterMovementModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Adapter used to recreate plotter commands as a list of PlotterMovementModel objects
 */
public class InterceptCoordinatesAdapterPlotterDriver implements IPlotter {
    /**
     * List of coordinates
     */
    private List<PlotterMovementModel> coordinates;

    public InterceptCoordinatesAdapterPlotterDriver() {
        coordinates = new LinkedList<>();
    }

    @Override
    public void setPosition(int i, int i1) {
        coordinates.add(new PlotterMovementModel(i, i1, false));
    }

    @Override
    public void drawTo(int i, int i1) {
        coordinates.add(new PlotterMovementModel(i, i1, true));
    }

    /**
     * Get list of coordinates
     * @return list of coordinates
     */
    public List<PlotterMovementModel> getCoordinates() {
        return new LinkedList<>(coordinates);
    }
}
