package com.musalasoft.drone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Medication extends BaseModel<Long> {

    public Medication(MedicationName name, Weight weight, MedicationCode code, Image image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    private MedicationName name;
    private Weight weight;
    private MedicationCode code;
    private Image image;

}
