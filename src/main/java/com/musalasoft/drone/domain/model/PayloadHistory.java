package com.musalasoft.drone.domain.model;

import com.musalasoft.drone.domain.contracts.Default;
import lombok.Getter;

@Getter
public class PayloadHistory extends Medication {

    private final SerialNumber serialNumber;

    @Default
    public PayloadHistory(SerialNumber serialNumber, MedicationName name, Weight weight, MedicationCode code, Image image) {
        super(name, weight, code, image);
        this.serialNumber = serialNumber;
    }

    public PayloadHistory(SerialNumber serialNumber, Medication medication) {
        this(serialNumber, medication.getName(), medication.getWeight(), medication.getCode(), medication.getImage());
    }
}
