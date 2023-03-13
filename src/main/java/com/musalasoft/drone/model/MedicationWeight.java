package com.musalasoft.drone.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class MedicationWeight {
    @NotNull
    Double weight;
}
