package com.musalasoft.drone.model;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class Weight {

    @Length(max = 500, message = "Weight limit  exceeded.")
    Double unit;

    public boolean isEmptyLimit() {
        return this.unit == null || this.unit == 0.0;
    }

    public Weight maxLimit() {
        return Weight.builder().unit(500.00).build();
    }
}
