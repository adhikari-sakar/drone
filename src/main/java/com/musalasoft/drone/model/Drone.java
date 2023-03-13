package com.musalasoft.drone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Drone extends BaseModel<Long> {

    private SerialNumber serialNumber;
    private DroneModel model;
    private WeightLimit weightLimit;
    private BatteryCapacity batteryCapacity;
    private DroneState state;
    private List<Medication> medications;
}
