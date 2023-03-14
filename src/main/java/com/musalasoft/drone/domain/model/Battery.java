package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;

@Value
@Builder
public class Battery {
    @Max(100)
    Double capacity;

    public boolean isDrained() {
        return capacity == null || capacity == 0.0;
    }

    public Battery full() {
        return Battery.of(100.00);
    }

    public boolean isLow() {
        return capacity < 25.00;
    }

    public boolean isFullCharged() {
        return capacity == 100;
    }

    public static Battery of(Double capacity) {
        return Battery.builder().capacity(capacity).build();
    }

    public Battery drain() {
        return Battery.builder().capacity(this.capacity - 25.00).build();
    }

    public Battery charge() {
        return !isFullCharged() ? Battery.of(this.capacity + 25.00) : this;
    }

}
