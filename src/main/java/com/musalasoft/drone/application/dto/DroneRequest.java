package com.musalasoft.drone.application.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DroneRequest {

    @Length(min = 1, max = 100)
    private String serialNumber;

    @NotNull
    @NotBlank
    private String model;

}
