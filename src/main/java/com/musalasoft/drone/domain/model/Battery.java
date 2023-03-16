package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Battery {

    Double capacity;

    public boolean isDrained() {
        return capacity == null || capacity == 0.0;
    }

    public boolean isLow() {
        return capacity < 25;
    }

    public boolean isFullCharged() {
        return capacity == 100;
    }

    public static Battery of(Double capacity) {
        return Battery.builder().capacity(capacity).build();
    }

    public Battery drain() {
        return Battery.builder().capacity(this.capacity - 20.00).build();
    }

    public Battery charge() {
        return !isFullCharged() ? Battery.of(capacity + 20.00) : this;
    }

}
