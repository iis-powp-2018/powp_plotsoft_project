package edu.iis.powp.controllers.ink;

import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.kis.powp.drawer.panel.DefaultDrawerFrame;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.ILine;
import edu.kis.powp.drawer.shape.LineFactory;

/**
 * Ink controller test.
 *
 * @author Klaudia
 */
public class EnoughInkForOneLineTest {
    /**
     * Ink controller test.
     */
    public static void main(String[] args) {
        DrawPanelController controller = new DrawPanelController();
        DefaultDrawerFrame.getDefaultDrawerFrame().setVisible(true);
        ILine line = LineFactory.getBasicLine();

        LineAdapterPlotterDriver linePlotter = new LineAdapterPlotterDriver(
                                                                controller, line, "Ink controller test");

        InkControllerInterface inkController = SimmulationInkController.getInstance();

        linePlotter.setPosition(0, 0);
        linePlotter.drawTo(500, 500);
        System.out.println("Ink level: " + inkController.getInkLevel());
    }
}
