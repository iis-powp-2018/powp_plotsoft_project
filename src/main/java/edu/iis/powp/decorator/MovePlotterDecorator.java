package edu.iis.powp.decorator;

import edu.iis.client.plottermagic.IPlotter;

public class MovePlotterDecorator extends PlotterDecorator {

    /**
     * Default constructor.
     *
     * @param originalPlotter instance of decorator.
     */
    public MovePlotterDecorator(IPlotter originalPlotter, String transformation) {
        super(originalPlotter, transformation);
    }

    /**
     * This method change value of y-axis by +50 and draw line.
     *
     * @param i  value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void drawTo(int i, int i1) {
        int[] tmp = extractingMethods(i, i1);
        originalPlotter.drawTo(tmp[0], tmp[1]);
    }

    /**
     * This method change value of y-axis by +50 and set position.
     *
     * @param i  value of x-axis.
     * @param i1 value of y-axis.
     */
    @Override
    public void setPosition(int i, int i1) {
        int[] tmp = extractingMethods(i, i1);
        originalPlotter.setPosition(tmp[0], tmp[1]);
    }

    private int[] extractingMethods(int i, int i1) {
        int tmp[] = {i, i1};
        if (super.transformation.equalsIgnoreCase("moveDown")) {
            tmp[1] += 50;
        } else if (super.transformation.equalsIgnoreCase("moveUp")) {
            tmp[1] -= 50;
        } else if (super.transformation.equalsIgnoreCase("moveLeft")) {
            tmp[0] -= 50;
        } else if (super.transformation.equalsIgnoreCase("moveRight")) {
            tmp[0] += 50;
        }
        return tmp;
    }
}
