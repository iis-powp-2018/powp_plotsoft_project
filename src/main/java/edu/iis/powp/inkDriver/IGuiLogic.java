package edu.iis.powp.inkDriver;

public interface IGuiLogic {
    void updateValueInGui(float value);
    void fillInk();
    void injectInkControl();
    void informationPopUp();
    void setGui(IGui gui);
}
