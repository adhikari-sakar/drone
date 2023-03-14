package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
@Builder
public class MedicationName {
    @Pattern(regexp = "^[\\w\\-]+$", message = "Medication name allows only letters, numbers and - or _ symbols")
    String name;
}
