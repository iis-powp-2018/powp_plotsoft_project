package edu.iis.powp.commandtransformer.model;

public class RotationCommandComponentModel implements CommandTransformerCreatorComponentModel {
    private Integer rotation = 0;

    public RotationCommandComponentModel() {
    }

    public RotationCommandComponentModel(Integer rotation) {
        this.rotation = rotation;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }
}
