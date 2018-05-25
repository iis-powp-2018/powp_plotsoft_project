package edu.iis.powp.controllers;

public interface InkControllerInterface {
    int getInkLevel();
    void reduceInkLevel(int level);
    void addInk(int level);
}
