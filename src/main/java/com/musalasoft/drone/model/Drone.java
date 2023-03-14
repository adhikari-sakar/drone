package com.musalasoft.drone.model;

import com.musalasoft.drone.exception.BatteryLowException;
import com.musalasoft.drone.exception.DroneLoadExceedException;

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

    private final SerialNumber serialNumber;
    private final DroneModel model;
    private final Weight weight;
    private final Battery battery;
    private final DroneState state;
    private final List<Medication> medications;

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

    public Drone loadItems(List<Medication> newMedications) {
        if (isOverloaded(newMedications))
            throw new DroneLoadExceedException("Weight limit exceeded.");
        if (isLowBattery())
            throw new BatteryLowException("Drone battery is low.");
        newMedications.forEach(load -> getMedications().add(load));
        return this;
    }

    private boolean isOverloaded(List<Medication> newLoads) {
        return currentLoad() + payloadWeight(newLoads) > Weight.maxLoad();
    }

    private Double currentLoad() {
        return payloadWeight(getMedications());
    }

    private Double payloadWeight(List<Medication> medications) {
        return medications
                .stream()
                .mapToDouble(medication -> medication.getWeight().getUnit())
                .sum();
    }

    private boolean isLowBattery() {
        return getBattery().isLow();
    }
}
