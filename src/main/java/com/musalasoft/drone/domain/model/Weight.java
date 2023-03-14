package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class Weight {

    @Min(1)
    @Max(500)
    @NotNull
    Double unit;

    public boolean isWeightLess() {
        return this.unit == null || this.unit < 1;
    }

    public static Weight of(Double unit) {
        return Weight.builder().unit(unit).build();
    }
}
