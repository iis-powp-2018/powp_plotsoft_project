package edu.iis.powp.controllers.ink;

import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.kis.powp.drawer.panel.DefaultDrawerFrame;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.ILine;
import edu.kis.powp.drawer.shape.LineFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Ink controller test.
 *
 * @author Klaudia
 */
public class InkControllerTest{

    private static InkControllerInterface inkController;
    private static ILine line;
    private static DrawPanelController controller;
    private static LineAdapterPlotterDriver linePlotterAdapter;
    private static LinePlotterInkControlDriverDecorator linePlotter;

    @BeforeClass
    public static void setupSuite(){
        controller = new DrawPanelController();
        DefaultDrawerFrame.getDefaultDrawerFrame().setVisible(true);
        line = LineFactory.getBasicLine();
        linePlotterAdapter = new LineAdapterPlotterDriver(
                controller, line, "Ink controller tests");
        linePlotter = new LinePlotterInkControlDriverDecorator(linePlotterAdapter);
        inkController = SimmulationInkController.getInstance();
    }

    @Before
    public void setup() {
        inkController.fillInk();
    }


    /**
     * Ink controller test showing one multiply lines when there is enough ink
     */
    @Test
    public void enoughInkForOneLineTest(){
        linePlotter.setPosition(0, 0);
        float lineLength = CalculateLineLength.calculateLineLength(0, 0, 500, 500);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(500, 500);
    }

    /**
     * Ink controller test showing drawing multiply lines when there is enough ink.
     */
    @Test
    public void enoughInkForMultiplyLinesTest(){
        linePlotter.setPosition(0, 0);
        float lineLength = CalculateLineLength.calculateLineLength(0, 0, 200, 200);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(200, 200);

        linePlotter.setPosition(200, 200);
        lineLength = CalculateLineLength.calculateLineLength(200, 200, 400, 0);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(400, 0);

        linePlotter.setPosition(400, 0);
        lineLength = CalculateLineLength.calculateLineLength(400, 0, 200, 100);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(200, 100);
    }

    /**
     * Ink controller test showing not drawing one line when there is not enough ink.
     */
    @Test
    public void notEnoughInkForOneLineTest(){
        linePlotter.setPosition(-300, -300);
        float lineLength = CalculateLineLength.calculateLineLength(-300, -300, 500, 500);
        assertThat(inkController.isInkEnough(lineLength), is(false));
    }

    /**
     * Ink controller test showing not drawing last line of multiply lines when there is not enough ink.
     */
    @Test
    public void notEnoughInkForMultiplyLinesTest(){
        linePlotter.setPosition(0, 0);
        float lineLength = CalculateLineLength.calculateLineLength(0, 0, 500, 500);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(200, 200);

        linePlotter.setPosition(200, 200);
        lineLength = CalculateLineLength.calculateLineLength(200, 200, 400, 400);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(400, 0);

        linePlotter.setPosition(400, 0);

        lineLength = CalculateLineLength.calculateLineLength(400, 0, 200, 100);
        assertThat(inkController.isInkEnough(lineLength), is(true));
        linePlotter.drawTo(200, 100);

        linePlotter.setPosition(400, 0);
        lineLength = CalculateLineLength.calculateLineLength(400, 0, 0, 0);
        assertThat(inkController.isInkEnough(lineLength), is(false));
    }
}
