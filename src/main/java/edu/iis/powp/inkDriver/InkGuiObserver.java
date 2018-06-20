package edu.iis.powp.inkDriver;

import edu.iis.powp.observer.Subscriber;

public class InkGuiObserver implements Subscriber {

    private IGuiLogic guiLogic;

    public InkGuiObserver(IGuiLogic inkGuiWindow) {
        super();
        this.guiLogic = inkGuiWindow;
    }

    @Override
    public void update() {
        if(guiLogic.checkIfInkControlUsed())
            guiLogic.injectInkControl();
    }

}
