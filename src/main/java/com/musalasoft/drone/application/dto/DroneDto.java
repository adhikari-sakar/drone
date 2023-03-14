package com.musalasoft.drone.application.dto;

import com.musalasoft.drone.model.DroneModel;
import com.musalasoft.drone.model.DroneState;
import lombok.Data;

import java.util.List;

@Data
public class DroneDto {
    private String serialNumber;
    private DroneModel model;
    private Double weightLimit;
    private Double batteryCapacity;
    private DroneState state;
    private List<MedicationDto> medications;
}
