package com.musalasoft.drone.domain.model;

import lombok.Getter;

public enum DroneModel {
    LIGHT_WEIGHT(100.00),
    MIDDLE_WEIGHT(200.00),
    CRUISER_WEIGHT(300.00),
    HEAVY_WEIGHT(400.00);

    @Getter
    private final Double weight;

    DroneModel(Double value) {
        this.weight = value;
    }

    public Weight weight() {
        return Weight.builder().unit(this.weight).build();
    }
}