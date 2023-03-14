package com.musalasoft.drone.domain;

import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.contracts.Task;
import com.musalasoft.drone.domain.model.Drone;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static com.musalasoft.drone.domain.model.DroneState.*;

@EnableScheduling
public class DroneScheduleTask implements Task {

    private final DroneRepository repository;

    public DroneScheduleTask(DroneRepository repository) {
        this.repository = repository;
    }

    @Override
    @Scheduled(fixedDelayString = "5000")
    public void execute() {

        startCharging();
        delivering();
        delivered();
        returning();

    }

    private void startCharging() {
        repository.findAllByDroneState(IDLE)
                .stream()
                .map(Drone::charge)
                .forEach(repository::save);
    }

    private void delivering() {
        repository.findAllByDroneState(LOADED)
                .stream()
                .map(Drone::delivering)
                .forEach(repository::save);
    }

    private void delivered() {
        repository.findAllByDroneState(DELIVERING)
                .stream()
                .map(Drone::delivered)
                .forEach(repository::save);
    }


    private void returning() {
        repository.findAllByDroneState(DELIVERED)
                .stream()
                .map(Drone::returnDrone)
                .forEach(repository::save);
    }

}
