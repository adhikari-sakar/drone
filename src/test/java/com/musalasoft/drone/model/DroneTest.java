package com.musalasoft.drone.model;

import com.musalasoft.drone.application.exception.BatteryLowException;
import com.musalasoft.drone.application.exception.DroneLoadExceedException;
import com.musalasoft.drone.application.exception.DroneNotReadyException;
import com.musalasoft.drone.domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.musalasoft.drone.domain.model.DroneModel.*;
import static com.musalasoft.drone.domain.model.DroneState.*;
import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    @Test
    void loadFails_ifDroneNotReady() {
        Drone drone = newDrone(LIGHT_WEIGHT, battery(100.00), IDLE, null);
        assertThrows(DroneNotReadyException.class, () -> drone.loadItems(List.of(medication(100.00))));
    }

    @Test
    void loadSuccess_100GMedicationFor_lightWeight_batteryFullDrone() {
        Drone drone = newDrone(LIGHT_WEIGHT, battery(100.00), LOADING, null);
        Drone loaded = drone.loadItems(List.of(medication(100.00)));
        assertEquals(LIGHT_WEIGHT.getWeight() + 100.00, loaded.getWeight().getUnit());
        assertFalse(drone.getMedications().isEmpty());
        assertEquals(1, drone.getMedications().size());
        assertFalse(drone.getHistories().isEmpty());
        assertEquals(1, drone.getHistories().size());
    }

    @Test
    void loadFailed_100GMedicationFor_lightWeight_batteryLowDrone() {
        Drone drone = newDrone(LIGHT_WEIGHT, battery(20.00), LOADING, null);
        assertThrows(BatteryLowException.class, () -> drone.loadItems(List.of(medication(100.00))));
    }


    @Test
    void loadSuccess_100GMedicationFor_middleWeight_batteryFullDrone() {
        Drone drone = newDrone(MIDDLE_WEIGHT, battery(100.00), LOADING, null);
        Drone loaded = drone.loadItems(List.of(medication(100.00)));
        assertEquals(MIDDLE_WEIGHT.getWeight() + 100.00, loaded.getWeight().getUnit());
        assertFalse(drone.getMedications().isEmpty());
        assertEquals(1, drone.getMedications().size());
        assertFalse(drone.getHistories().isEmpty());
        assertEquals(1, drone.getHistories().size());
    }

    @Test
    void loadFailed_100GMedicationFor_middleWeight_batteryLowDrone() {
        Drone drone = newDrone(MIDDLE_WEIGHT, battery(20.00), IDLE, null);
        assertThrows(BatteryLowException.class, () -> drone.loadItems(List.of(medication(100.00))));
    }

    @Test
    void loadSuccess_100GMedicationFor_cruiseWeight_batteryFullDrone() {
        Drone drone = newDrone(CRUISER_WEIGHT, battery(100.00), LOADING, null);
        Drone loaded = drone.loadItems(List.of(medication(100.00)));
        assertEquals(CRUISER_WEIGHT.getWeight() + 100.00, loaded.getWeight().getUnit());
        assertFalse(drone.getMedications().isEmpty());
        assertEquals(1, drone.getMedications().size());
        assertFalse(drone.getHistories().isEmpty());
        assertEquals(1, drone.getHistories().size());
    }

    @Test
    void loadFailed_100GMedicationFor_cruiseWeight_batteryLowDrone() {
        Drone drone = newDrone(CRUISER_WEIGHT, battery(20.00), LOADING, null);
        assertThrows(BatteryLowException.class, () -> drone.loadItems(List.of(medication(100.00))));
    }

    @Test
    void loadSuccess_100GMedicationFor_heavyWeight_batteryFullDrone() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(100.00), LOADING, null);
        Drone loaded = drone.loadItems(List.of(medication(100.00)));
        assertEquals(HEAVY_WEIGHT.getWeight() + 100.00, loaded.getWeight().getUnit());
        assertFalse(drone.getMedications().isEmpty());
        assertEquals(1, drone.getMedications().size());
        assertFalse(drone.getHistories().isEmpty());
        assertEquals(1, drone.getHistories().size());
    }

    @Test
    void loadFailed_200GMedicationFor_heavyWeight_batteryFullDrone() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(100.00), LOADING, null);
        assertThrows(DroneLoadExceedException.class, () -> drone.loadItems(List.of(medication(200.00))));
    }

    @Test
    void loadFailed_100GMedicationFor_heavyWeight_batteryLowDrone() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(20.00), LOADING, null);
        assertThrows(BatteryLowException.class, () -> drone.loadItems(List.of(medication(100.00))));
    }

    @Test
    void charge() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(20.00), IDLE, null);
        assertEquals(40.00, drone.charge().getBattery().getCapacity());
        assertEquals(LOADING, drone.getState());
    }

    @Test
    void deliver() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(50.00), IDLE, null);
        assertEquals(30.00, drone.deliver().getBattery().getCapacity());
        assertEquals(DELIVERING, drone.getState());
    }

    @Test
    void delivered() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(50.00), IDLE, null);
        drone.getMedications().add(medication(100.00));
        assertEquals(30.00, drone.delivered().getBattery().getCapacity());
        assertEquals(HEAVY_WEIGHT.getWeight(), drone.getWeight().getUnit());
        assertEquals(DELIVERED, drone.getState());
        assertTrue(drone.getMedications().isEmpty());
    }

    @Test
    void returning() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(50.00), IDLE, null);
        assertEquals(30.00, drone.returnDrone().getBattery().getCapacity());
        assertEquals(RETURNING, drone.getState());
    }

    @Test
    void land() {
        Drone drone = newDrone(HEAVY_WEIGHT, battery(50.00), IDLE, null);
        assertEquals(30.00, drone.land().getBattery().getCapacity());
        assertEquals(IDLE, drone.getState());
    }

    public static Drone newDrone(DroneModel model, Battery battery, DroneState state, List<Medication> medications) {
        return new Drone(serialNumber(), model, model.weight(), battery, state, medications, null);
    }

    private static SerialNumber serialNumber() {
        return SerialNumber.builder().id(UUID.randomUUID().toString()).build();
    }

    private static Battery battery(Double capacity) {
        return Battery.builder().capacity(capacity).build();
    }

    private Medication medication(Double weight) {
        return new Medication(MedicationName.builder().name("Test_M").build(),
                Weight.of(weight), MedicationCode.builder().code("M001").build(),
                Image.builder().url("/src/M.png").build());
    }

}