package com.musalasoft.drone.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;

@Value
@Builder
public class Battery {

    @Max(100)
    Double capacity;

    public boolean isDrained() {
        return this.capacity == null || this.capacity == 0.0;
    }

    public Battery maxCapacity() {
        return Battery.builder().capacity(100.00).build();
    }
}
