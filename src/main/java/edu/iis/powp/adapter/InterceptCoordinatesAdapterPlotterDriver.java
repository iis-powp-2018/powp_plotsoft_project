package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.model.PlotterMovementModel;

import java.util.LinkedList;
import java.util.List;

public class InterceptCoordinatesAdapterPlotterDriver implements IPlotter {
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

    public List<PlotterMovementModel> getCoordinates() {
        return new LinkedList<>(coordinates);
    }
}
