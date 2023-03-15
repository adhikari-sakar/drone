package com.musalasoft.drone.application.dto;

import com.musalasoft.drone.domain.validators.Regex;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class MedicationRequest {
    @NotNull
    @Regex(regex = "^[\\w\\-]+$",
            message = "Medication name allows only letters, numbers and - or _ symbols",
            required = true)
    private String name;
    @Min(1)
    @NotNull
    @Positive()
    private Double weight;
    @NotNull
    @Regex(regex = "^[\\dA-Z_]+$",
            message = "Medication code only allows uppercase letters, underscores and numbers.",
            required = true)
    private String code;
    @NotNull
    private String imageUrl;
}
