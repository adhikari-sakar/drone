package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Value
@Builder
public class SerialNumber {
    @NotBlank
    @Length(min = 1, max = 100)
    String id;

    public boolean isNotGenerated() {
        return id == null || id.isEmpty();
    }

    public SerialNumber newSerialNumber() {
        return SerialNumber.builder().id(UUID.randomUUID().toString()).build();
    }
}
