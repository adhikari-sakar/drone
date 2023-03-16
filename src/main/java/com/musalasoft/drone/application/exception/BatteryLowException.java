package com.musalasoft.drone.application.exception;

public class BatteryLowException extends DroneException {
    public BatteryLowException(String message) {
        super(message);
    }
}
