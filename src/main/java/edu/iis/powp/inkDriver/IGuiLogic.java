package edu.iis.powp.inkDriver;

public interface IGuiLogic {
    void updateValueInGui(float value);
    void updateValueInGui(float value, float value2);
    void fillInk();
    void injectInkControl();
    void informationPopUp();
    void setGui(IGui gui);
    void updateMaxInkLevel(float value);
    void changeInkLevel(int i);
}
