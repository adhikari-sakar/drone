package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Weight {

    Double unit;
    public boolean isWeightLess() {
        return this.unit == null || this.unit < 0;
    }

    public static Weight of(Double unit) {
        return Weight.builder().unit(unit).build();
    }
}
