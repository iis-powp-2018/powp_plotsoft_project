package edu.iis.powp.commandtransformer.model;

public class MoveCommandComponentModel implements CommandTransformerCreatorComponentModel {
    private Integer movementX = 0;
    private Integer movementY = 0;

    public MoveCommandComponentModel() {
    }

    public MoveCommandComponentModel(Integer movementX, Integer movementY) {
        this.movementX = movementX;
        this.movementY = movementY;
    }

    public Integer getMovementX() {
        return movementX;
    }

    public void setMovementX(Integer movementX) {
        this.movementX = movementX;
    }

    public Integer getMovementY() {
        return movementY;
    }

    public void setMovementY(Integer movementY) {
        this.movementY = movementY;
    }
}
