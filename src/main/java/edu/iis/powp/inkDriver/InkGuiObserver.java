package edu.iis.powp.inkDriver;

import edu.iis.powp.observer.Subscriber;

public class InkGuiObserver implements Subscriber {

    private InkGui inkGuiWindow;

    public InkGuiObserver(InkGui inkGuiWindow) {
        super();
        this.inkGuiWindow = inkGuiWindow;
    }

    @Override
    public void update() {
        inkGuiWindow.changePlotter();
    }

}
