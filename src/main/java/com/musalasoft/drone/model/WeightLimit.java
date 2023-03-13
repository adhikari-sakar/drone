package com.musalasoft.drone.model;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class WeightLimit {

    @Length(min = 1, max = 500)
    Double limit;
}
