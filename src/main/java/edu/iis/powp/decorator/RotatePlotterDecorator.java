package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class RotatePlotterDecorator extends PlotterDecorator {

    /**
     * Default constructor.
     * @param originalPlotter instance of decorator.
     */
    public RotatePlotterDecorator(IPlotter originalPlotter, String transformation) {
        super(originalPlotter, transformation);
    }

    /**
     * This method rotate line by -45 radians and draw it.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        int[] tmp = extractingMethods(i, i1);
        originalPlotter.drawTo(tmp[0], tmp[1]);
    }

    /**
     * This method rotate line by -45 radians and set position of the rotated line.
     * @param i value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        int[] tmp = extractingMethods(i, i1);
        originalPlotter.setPosition(tmp[0], tmp[1]);
    }

    private int[] extractingMethods(int i, int i1) {
        int x = i;
        int y = i1;
        int tmp[] = {i, i1};
        if(super.transformation.equalsIgnoreCase("rotateLeft")) {
            tmp[0] = (int) (tmp[0] * Math.cos(Math.toRadians(-45)) - tmp[1] * Math.sin(Math.toRadians(-45)));
            tmp[1] = (int) (y * Math.cos(Math.toRadians(-45)) + x * Math.sin(Math.toRadians(-45)));
        }
        else if(super.transformation.equalsIgnoreCase("rotateRight")) {
            tmp[0] = (int) (i * Math.cos(Math.toRadians(45)) - tmp[1] * Math.sin(Math.toRadians(45)));
            tmp[1] = (int) (y * Math.cos(Math.toRadians(45)) + x * Math.sin(Math.toRadians(45)));
        }
        return tmp;
    }
}
