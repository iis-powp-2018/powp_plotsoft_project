package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.ClientPlotter;
import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class InkGuiLogic implements IGuiLogic {

    private IController IController;
    private Application application;
    private IPlotter currentPlotter, rawIPlotter;
    private IGui gui;
    private boolean isShowedAlert = false, setup = true, isCriticalChargeUsed = false, isInkControlUsed = false;
    private float maximumInkLevel = 500;
    private float remainingInkLevel = 500;
    private DefaultLineKeeper defaultLineKeeper = new DefaultLineKeeper();

    @Override
    public void updateValueInGui(float value) {
        gui.updateValueInGui(value, maximumInkLevel);
    }

    @Override
    public void updateValueInGui(float value, float value2) {
        gui.updateValueInGui(value, value2);
    }

    @Override
    public void fillInk(){
        isShowedAlert = false;
        this.remainingInkLevel = maximumInkLevel;
        gui.updateValueInGui(remainingInkLevel, maximumInkLevel);
        IController.updateValueInController(remainingInkLevel);
    }

    @Override
    public void injectInkControl(){
        setUndecoratedPlotter(application.getDriverManager().getCurrentPlotter());

        try {
            defaultLineKeeper.checkLine(rawIPlotter);
            IPlotter plotter;
            if (!isCriticalChargeUsed)
                plotter = new InkController(rawIPlotter, remainingInkLevel, this);
            else
                plotter = new InkControllerWithCriticalCharge(rawIPlotter, remainingInkLevel, this);
            application.getDriverManager().setCurrentPlotter(plotter);
            IController = (IController) plotter;
        } catch (ClassCastException ex) {
            //user probably want assign controller for real plotter
            IPlotter plotter;
            if (!isCriticalChargeUsed)
                plotter = new InkController(rawIPlotter, remainingInkLevel, this, true);
            else
                plotter = new InkControllerWithCriticalCharge(rawIPlotter, remainingInkLevel, this);
            application.getDriverManager().setCurrentPlotter(plotter);
            IController = (IController) plotter;
        }
    }

    private void setUndecoratedPlotter(IPlotter plotter) {
        if(!(plotter instanceof InkController || plotter instanceof InkControllerWithCriticalCharge))
            rawIPlotter = plotter;
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

    @Override
    public void updateMaxInkLevel(float value) {
        this.maximumInkLevel = value;
        updateValueInGui(remainingInkLevel, maximumInkLevel);
        if(setup)
            setup = false;
        else
            fillInk();
    }


    public void setApplication(Application application){
        this.application = application;
        //hack to run injectInkControl()
        currentPlotter = application.getDriverManager().getCurrentPlotter();
        application.getDriverManager().setCurrentPlotter(currentPlotter);
    }

    @Override
    public void changeInkLevel(int value)
    {
        this.maximumInkLevel += value;
        if(maximumInkLevel < 300)
            maximumInkLevel = 300;
        updateValueInGui(remainingInkLevel, maximumInkLevel);
        fillInk();
    }

    @Override
    public void useInkController(ActionEvent e){
        JCheckBox checkBox = (JCheckBox)e.getSource();
        if(checkBox.isSelected()) {
            isInkControlUsed = true;
            currentPlotter = application.getDriverManager().getCurrentPlotter();
            application.getDriverManager().setCurrentPlotter(currentPlotter);

        } else {
            isInkControlUsed = false;
            if(!(rawIPlotter instanceof ClientPlotter))
                defaultLineKeeper.checkLine(rawIPlotter);
            application.getDriverManager().setCurrentPlotter(rawIPlotter);
        }
    }

    @Override
    public void useCriticalCharge(ActionEvent e){
        JCheckBox checkBox = (JCheckBox)e.getSource();
        isCriticalChargeUsed=checkBox.isSelected();
        //hack to run injectInkControl()
        currentPlotter = application.getDriverManager().getCurrentPlotter();
        application.getDriverManager().setCurrentPlotter(currentPlotter);
    }

    @Override
    public boolean checkIfInkControlUsed(){
        return isInkControlUsed;
    }
}
