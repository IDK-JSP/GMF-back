package com.fmg.gmf_core.entitys;

import jakarta.validation.constraints.NotBlank;

public class Measurement {

    private int id_measurement;
    @NotBlank(message = "Un nom de mesure est requis")
    private String name;

    //Masse / volume / autre
    @NotBlank(message = "Un type de mesure est requis (masse, volume, autre)")
    private String type;

    public Measurement(int id_measurement, String name, String type) {
        this.id_measurement = id_measurement;
        this.name = name;
        this.type = type;
    }

    public int getId_measurement() {
        return id_measurement;
    }

    public void setId_measurement(int id_measurement) {
        this.id_measurement = id_measurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
