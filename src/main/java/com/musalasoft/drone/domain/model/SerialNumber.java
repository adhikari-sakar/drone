package com.musalasoft.drone.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SerialNumber {

    String id;

    public boolean isNotProvided() {
        return id == null || id.isEmpty();
    }

    public SerialNumber generate() {
        return SerialNumber.builder().id(UUID.randomUUID().toString()).build();
    }
}
