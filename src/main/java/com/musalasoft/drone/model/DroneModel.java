package com.musalasoft.drone.model;

import lombok.Getter;

public enum DroneModel {
    LIGHT_WIGHT("Lightweight"),
    MIDDLE_WEIGHT("MiddleWeight"),
    CRUISER_WEIGHT("Cruiserweight,"),
    HEAVY_WEIGHT("Heavyweight");

    @Getter
    private final String value;

    DroneModel(String value) {
        this.value = value;
    }
}