package edu.iis.powp.adapter;

import edu.iis.powp.observer.Subscriber;

public class InkGuiObserver implements Subscriber {

    private InkGui inkGuiWindow;

    public InkGuiObserver(InkGui inkGuiWindow) {
        super();
        this.inkGuiWindow = inkGuiWindow;
    }

    @Override
    public void update() {
        System.out.println("Reaguje na zmiane plottera");
    }

}
