package edu.iis.powp.controllers.ink;


public interface Observed {
    void addObservator(Observator o);
    void removeObservator(Observator o);
    void notifyObservators();
}
