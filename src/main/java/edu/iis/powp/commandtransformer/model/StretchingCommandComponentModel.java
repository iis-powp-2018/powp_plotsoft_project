package edu.iis.powp.commandtransformer.model;

public class StretchingCommandComponentModel implements CommandTransformerCreatorComponentModel {
    private Boolean stretchX = false;
    private Boolean stretchY = false;
    private Integer stretchValue = 0;

    public StretchingCommandComponentModel() {
    }

    public StretchingCommandComponentModel(Boolean stretchX, Boolean stretchY, Integer stretchValue) {
        this.stretchX = stretchX;
        this.stretchY = stretchY;
        this.stretchValue = stretchValue;
    }

    public Boolean getStretchX() {
        return stretchX;
    }

    public void setStretchX(Boolean stretchX) {
        this.stretchX = stretchX;
    }

    public Boolean getStretchY() {
        return stretchY;
    }

    public void setStretchY(Boolean stretchY) {
        this.stretchY = stretchY;
    }

    public Integer getStretchValue() {
        return stretchValue;
    }

    public void setStretchValue(Integer stretchValue) {
        this.stretchValue = stretchValue;
    }
}
