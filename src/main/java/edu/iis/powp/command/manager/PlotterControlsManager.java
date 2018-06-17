package edu.iis.powp.command.manager;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.Application;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.decorator.*;
import edu.iis.powp.features.CommandsFeature;
import edu.iis.powp.features.DrawerFeature;

public class PlotterControlsManager {

    private Application application;
    private IPlotter newPlotter;

    public PlotterControlsManager(Application application) {
        this.application = application;
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveDownWindow() {
        newPlotter = new MoveDownPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveUpWindow() {
        newPlotter = new MoveUpPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveLeftWindow() {
        newPlotter = new MoveLeftPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveRightWindow() {
        newPlotter = new MoveRightPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void flipHorizontalWindow() {
        newPlotter = new FlipHorizontalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void flipVerticalWindow() {
        newPlotter = new FlipVerticalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void rotateRightWindow() {
        newPlotter = new RotateRightPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void rotateLeftWindow() {
        newPlotter = new RotateLeftPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void zoomOutWindow() {
        newPlotter = new ZoomOutPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void zoomInWindow() {
        newPlotter = new ZoomInPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }
    /**
     * This method holds information about currently drawn simulation.
     */
    private void commandHistory() {
        for (String command: CommandsFeature.commandList) {
            if (command.equals("figureScript1")) {
                FiguresJoe.figureScript1(application.getDriverManager().getCurrentPlotter());
            }
            if (command.equals("figureScript2")) {
                FiguresJoe.figureScript2(application.getDriverManager().getCurrentPlotter());
            }
            if (command.equals("secretCommand")) {
                IPlotterCommand iPlotterCommand = CommandsFeature.getPlotterCommandManager().getCurrentCommand();
                iPlotterCommand.execute(application.getDriverManager().getCurrentPlotter());
            }
        }
    }
}
