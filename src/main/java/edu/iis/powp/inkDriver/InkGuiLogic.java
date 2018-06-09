package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;

public class InkGuiLogic implements IGuiLogic {

    private IController IController;
    private Application application;
    private IPlotter currentPlotter;
    private IGui gui;
    private boolean isShowedAlert = false;
    private float initialInkLvl;

    @Override
    public void updateValueInGui(float value) {
        gui.updateValueInGui(value);
    }

    @Override
    public void fillInk(){
        isShowedAlert = false;
        IController.updateValueInController(500);//TO FIX STATIC VALUE
        gui.updateValueInGui(500);
    }

    @Override
    public void injectInkControl(){
        try {
            IPlotter plotter = new InkController(application.getDriverManager().getCurrentPlotter(), initialInkLvl, this);
            application.getDriverManager().setCurrentPlotter(plotter);
            IController = (IController) plotter;
        }
        catch (ClassCastException ex){
            //user probably want assign controller for real plotter
            IPlotter plotter = new InkController(application.getDriverManager().getCurrentPlotter(), initialInkLvl, this, true);
            application.getDriverManager().setCurrentPlotter(plotter);
            IController = (IController) plotter;
        }
    }

    @Override
    public void informationPopUp() {
        if(!isShowedAlert){
            gui.informationPopUp();
            isShowedAlert = true;
        }
    }

    @Override
    public void setGui(IGui gui) {
        this.gui = gui;
    }

    public void setInitialInkLvl(float initialInkLvl){
        this.initialInkLvl = initialInkLvl;
        updateValueInGui(initialInkLvl);
    }

    public void setApplication(Application application){
        this.application = application;
        //hack to run injectInkControl()
        currentPlotter = application.getDriverManager().getCurrentPlotter();
        application.getDriverManager().setCurrentPlotter(currentPlotter);
    }
}
