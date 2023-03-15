package com.musalasoft.drone.domain.model;

import com.musalasoft.drone.application.exception.DroneLoadExceedException;
import com.musalasoft.drone.application.exception.DroneNotReadyException;

import java.util.ArrayList;
import java.util.List;

import static com.musalasoft.drone.domain.model.DroneState.*;
import static java.util.Optional.ofNullable;

public class Drone extends BaseModel<Long> {

    private static final Double MAX_WEIGHT = 500.00;

    public Drone(SerialNumber serialNumber, DroneModel model, Weight weight, Battery battery, DroneState state, List<Medication> medications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
        this.medications = ofNullable(medications).orElseGet(ArrayList::new);
    }

    private final SerialNumber serialNumber;
    private final DroneModel model;
    private Weight weight;
    private Battery battery;
    private DroneState state;
    private final List<Medication> medications;

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
                .map(b -> Battery.of(20.00))
                .orElse(battery);
    }

    public DroneState getState() {
        return ofNullable(this.state).orElse(DroneState.IDLE);
    }

    public List<Medication> getMedications() {
        return this.medications;
    }

    public Drone loadItems(List<Medication> newMedications) {
        if (!isReady() || isLowBattery())
            throw new DroneNotReadyException("Drone is not ready. State: " + this.state + " Battery: " + this.battery.getCapacity());
        if (isOverloaded(newMedications))
            throw new DroneLoadExceedException("Weight limit exceeded. Max Weight : " + MAX_WEIGHT + " Current Weight: " + this.currentWeight());
        newMedications.forEach(medication -> getMedications().add(medication));
        this.state = LOADED;
        return this;
    }

    private boolean isReady() {
        return state.isReady();
    }

    private boolean isOverloaded(List<Medication> newLoads) {
        return currentWeight() + payloadWeight(newLoads) > MAX_WEIGHT;
    }

    private Double currentWeight() {
        if (state.isLoaded())
            return model.getWeight() + payloadWeight(getMedications());
        return model.getWeight();
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
        if (!battery.isLow())
            this.state = LOADING;
        return this;
    }

    private void drainBattery() {
        this.battery = battery.drain();
    }

    public Drone deliver() {
        this.state = DELIVERING;
        drainBattery();
        return this;
    }

    public Drone delivered() {
        this.state = DELIVERED;
        unload();
        drainBattery();
        return this;
    }

    private void unload() {
        this.weight = model.weight();
    }

    public Drone returnDrone() {
        this.state = RETURNING;
        drainBattery();
        return this;
    }

    public Drone land() {
        this.state = IDLE;
        drainBattery();
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " serial_number=" + serialNumber.getId() +
                "  weight=" + weight.getUnit() +
                ", battery=" + battery.getCapacity() +
                ", state=" + state.name() +
                ", medications_size=" + currentMedicationSize() +
                ", medications_weight=" + currentMedicationLoad() +
                '}';
    }

    private Integer currentMedicationSize() {
        if (state.isLoaded())
            return medications.size();
        return 0;
    }

    private Double currentMedicationLoad() {
        if (state.isLoaded())
            return payloadWeight(getMedications());
        return 0.0;
    }
}
