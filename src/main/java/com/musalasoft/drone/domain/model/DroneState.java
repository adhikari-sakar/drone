package com.musalasoft.drone.domain.model;

public enum DroneState {

    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING;

    public boolean isLoaded() {
        return this == LOADED || this == DELIVERING;
    }

    public boolean isReady() {
        return this == IDLE;
    }
}
