package edu.iis.powp.command.manager;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.Application;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.decorator.*;
import edu.iis.powp.features.CommandsFeature;
import edu.iis.powp.features.DrawerFeature;

import java.util.ArrayList;

public class PlotterControlsManager {

    private Application application;
    private IPlotter newPlotter;
    private Boolean history;

    public PlotterControlsManager(Application application) {
        this.application = application;
        history = true;
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveDownWindow() {
        newPlotter = new MovePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "moveDown");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveUpWindow() {
        newPlotter = new MovePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "moveUp");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveLeftWindow() {
        newPlotter = new MovePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "moveLeft");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void moveRightWindow() {
        newPlotter = new MovePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "moveRight");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void flipHorizontalWindow() {
        newPlotter = new FlipPlotterDecorator(application.getDriverManager().getCurrentPlotter(), "flipHorizontal");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void flipVerticalWindow() {
        newPlotter = new FlipPlotterDecorator(application.getDriverManager().getCurrentPlotter(), "flipVertical");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void rotateRightWindow() {
        newPlotter = new RotatePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "rotateRight");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void rotateLeftWindow() {
        newPlotter = new RotatePlotterDecorator(application.getDriverManager().getCurrentPlotter(), "rotateLeft");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void zoomOutWindow() {
        newPlotter = new ZoomPlotterDecorator(application.getDriverManager().getCurrentPlotter(), "zoomOut");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method changes data injected into actual IPlotter to change its behaviour.
     */
    public void zoomInWindow() {
        newPlotter = new ZoomPlotterDecorator(application.getDriverManager().getCurrentPlotter(), "zoomIn");
        application.getDriverManager().setCurrentPlotter(newPlotter);
        DrawerFeature.getDrawerController().clearPanel();
        commandHistory();
    }

    /**
     * This method is enabling / disabling use of commandHistory method
     */
    public void enableHistory(){
        history = !history;
    }

    public static ArrayList<String> commandList = new ArrayList<>();

    /**
     * This method holds information about currently drawn simulation.
     */
    private void commandHistory() {
        if(history)
        for (String command: commandList) {
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
