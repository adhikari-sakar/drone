package com.musalasoft.drone.application.dto;

import lombok.Data;

@Data
public class NewDroneRequest {

    private String serialNumber;
    private String model;
    private Double weightLimit;
    private Double batteryCapacity;
    private String state;

}
