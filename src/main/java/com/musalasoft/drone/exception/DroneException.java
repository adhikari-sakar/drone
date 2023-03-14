package com.musalasoft.drone.exception;

public abstract class DroneException extends RuntimeException {
    public DroneException(String message) {
        super(message);
    }
}
