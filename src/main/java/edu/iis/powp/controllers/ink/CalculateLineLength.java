package edu.iis.powp.controllers.ink;

public class CalculateLineLength {

    public static float calculateLineLength(int startX, int startY, int x, int y) {
        return (float) Math.sqrt((startX - x) * (startX - x) + (startY - y) * (startY - y));
    }
}
