package com.musalasoft.drone.domain.model;

import com.musalasoft.drone.exception.BatteryLowException;
import com.musalasoft.drone.exception.DroneLoadExceedException;

import java.util.ArrayList;
import java.util.List;

import static com.musalasoft.drone.domain.model.DroneState.*;
import static java.util.Optional.ofNullable;

public class Drone extends BaseModel<Long> {
    public Drone(SerialNumber serialNumber, DroneModel model, Weight weight, Battery battery, DroneState state, List<Medication> medications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
        this.medications = ofNullable(medications).orElseGet(ArrayList::new);
    }

    private SerialNumber serialNumber;
    private DroneModel model;
    private Weight weight;
    private Battery battery;
    private DroneState state;
    private List<Medication> medications;

    public SerialNumber getSerialNumber() {
        return ofNullable(this.serialNumber)
                .filter(SerialNumber::isNotGenerated)
                .map(SerialNumber::newSerialNumber)
                .orElse(this.serialNumber);
    }

    public DroneModel getModel() {
        return this.model;
    }

    public Weight getWeight() {
        return ofNullable(this.weight)
                .filter(Weight::isWeightLess)
                .map(w -> getModel().weight())
                .orElseGet(() -> Weight.of(currentWeight()));
    }

    public Battery getBattery() {
        return ofNullable(battery)
                .filter(Battery::isDrained)
                .map(Battery::full)
                .orElse(battery);
    }

    public DroneState getState() {
        return ofNullable(this.state).orElse(DroneState.IDLE);
    }

    public List<Medication> getMedications() {
        return this.medications;
    }

    public Drone loadItems(List<Medication> newMedications) {
        this.state = LOADING;
        if (isLowBattery())
            throw new BatteryLowException("Drone battery is low.");
        if (isOverloaded(newMedications))
            throw new DroneLoadExceedException("Weight limit exceeded.");
        newMedications.forEach(medication -> getMedications().add(medication));
        this.state = LOADED;
        return this;
    }

    private boolean isOverloaded(List<Medication> newLoads) {
        return currentWeight() + payloadWeight(newLoads) > maxWeight();
    }

    private Double currentWeight() {
        return weight.getUnit() + payloadWeight(getMedications());
    }

    private Double maxWeight() {
        return 500.00;
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

    public Drone charge() {
        this.battery = battery.charge();
        return this;
    }

    private void dropBattery() {
        this.battery = battery.drop();
    }

    public Drone delivering() {
        this.state = DELIVERING;
        dropBattery();
        return this;
    }

    public Drone delivered() {
        this.state = DELIVERED;
        unload();
        dropBattery();
        return this;
    }

    private void unload() {
        getMedications().clear();
    }

    public Drone returnDrone() {
        this.state = RETURNING;
        dropBattery();
        return this;
    }

    public Drone land() {
        this.state = IDLE;
        dropBattery();
        return this;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "serialNumber=" + serialNumber.getId() +
                ", model=" + model.name() +
                ", weight=" + weight.getUnit() +
                ", battery=" + battery.getCapacity() +
                ", state=" + state.name() +
                ", medications=" + medications +
                '}';
    }
}
