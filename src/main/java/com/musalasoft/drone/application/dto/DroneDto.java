package com.musalasoft.drone.application.dto;

import com.musalasoft.drone.domain.model.DroneModel;
import com.musalasoft.drone.domain.model.DroneState;
import lombok.Data;

import java.util.List;

@Data
public class DroneDto {
    private String serialNumber;
    private DroneModel model;
    private Double weight;
    private Double battery;
    private DroneState state;
    private List<MedicationDto> medications;
}
