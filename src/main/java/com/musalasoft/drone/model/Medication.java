package com.musalasoft.drone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Medication extends BaseModel<Long> {
    private MedicationName name;
    private MedicationWeight weight;
    private MedicationCode code;
    private Image image;

}
