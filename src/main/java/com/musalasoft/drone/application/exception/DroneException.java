package com.musalasoft.drone.application.exception;

public abstract class DroneException extends RuntimeException {
    public DroneException(String message) {
        super(message);
    }
}
