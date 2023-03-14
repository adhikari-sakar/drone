package com.musalasoft.drone.domain.model;

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

    private final MedicationName name;
    private final Weight weight;
    private final MedicationCode code;
    private final Image image;

}
