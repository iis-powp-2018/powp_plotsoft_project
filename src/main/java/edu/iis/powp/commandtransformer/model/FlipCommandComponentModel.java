package edu.iis.powp.commandtransformer.model;

public class FlipCommandComponentModel implements CommandTransformerCreatorComponentModel {
    private Boolean flipX = false;
    private Boolean flipY = false;

    public FlipCommandComponentModel() {
    }

    public FlipCommandComponentModel(Boolean flipX, Boolean flipY) {
        this.flipX = flipX;
        this.flipY = flipY;
    }

    public Boolean getFlipX() {
        return flipX;
    }

    public void setFlipX(Boolean flipX) {
        this.flipX = flipX;
    }

    public Boolean getFlipY() {
        return flipY;
    }

    public void setFlipY(Boolean flipY) {
        this.flipY = flipY;
    }
}
