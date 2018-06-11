package edu.iis.powp.commandtransformer.model;

public class GraduationCommandComponentModel implements CommandTransformerCreatorComponentModel {
    private Integer graduation = 0;

    public GraduationCommandComponentModel() {
    }

    public GraduationCommandComponentModel(Integer graduation) {
        this.graduation = graduation;
    }

    public Integer getGraduation() {
        return graduation;
    }

    public void setGraduation(Integer graduation) {
        this.graduation = graduation;
    }
}
