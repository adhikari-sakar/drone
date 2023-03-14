package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
@Builder
public class MedicationCode {

    @Pattern(regexp = "^[\\dA-Z_]+$",
            message = "Medication code only allows uppercase letters, underscores and numbers.")
    String code;
}
