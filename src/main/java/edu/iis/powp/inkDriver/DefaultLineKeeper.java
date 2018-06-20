package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.kis.powp.drawer.shape.ILine;

import java.util.HashMap;

public class DefaultLineKeeper {

    HashMap<IPlotter, ILine> lineHashMap = new HashMap<>();

    public void checkLine(IPlotter rawIPlotter) {
        if(!lineHashMap.containsKey(rawIPlotter)){
            lineHashMap.put(rawIPlotter, ((LineAdapterPlotterDriver)rawIPlotter).getLine());
        } else {
            ILine tmp = lineHashMap.get(rawIPlotter);
            ((LineAdapterPlotterDriver)rawIPlotter).setLine(tmp);
        }
    }
}
