package com.musalasoft.drone.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

public class Drone extends BaseModel<Long> {

    public Drone(SerialNumber serialNumber, DroneModel model, Weight weight, Battery battery, DroneState state, List<Medication> medications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
        this.medications = medications;
    }

    private SerialNumber serialNumber;
    private DroneModel model;
    private Weight weight;
    private Battery battery;
    private DroneState state;
    private List<Medication> medications;

    public SerialNumber getSerialNumber() {
        return ofNullable(serialNumber)
                .filter(SerialNumber::isNotGenerated)
                .map(SerialNumber::newSerialNumber)
                .orElse(serialNumber);
    }

    public DroneModel getModel() {
        return model;
    }

    public Weight getWeight() {
        return ofNullable(weight)
                .filter(Weight::isEmptyLimit)
                .map(Weight::maxLimit)
                .orElse(weight);
    }

    public Battery getBattery() {
        return ofNullable(battery)
                .filter(Battery::isDrained)
                .map(Battery::maxCapacity)
                .orElse(battery);
    }

    public DroneState getState() {
        return ofNullable(state).orElse(DroneState.IDLE);
    }

    public List<Medication> getMedications() {
        return ofNullable(medications).orElseGet(ArrayList::new);
    }

    public Drone loadItems(List<Medication> medications) {
        medications.forEach(medication -> getMedications().add(medication));
        return this;
    }
}
