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
public class NotEnoughInkForMultiplyLinesTest {
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
        linePlotter.drawTo(200, 200);
        System.out.println("Ink level: " + inkController.getInkLevel());

        linePlotter.setPosition(200, 200);
        linePlotter.drawTo(400, 0);
        System.out.println("Ink level: " + inkController.getInkLevel());

        linePlotter.setPosition(400, 0);
        linePlotter.drawTo(200, 100);
        System.out.println("Ink level: " + inkController.getInkLevel());

        linePlotter.setPosition(400, 0);
        linePlotter.drawTo(0, 0);
        System.out.println("Ink level: " + inkController.getInkLevel());
    }
}
