package com.musalasoft.drone.application;

import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.contracts.Task;
import com.musalasoft.drone.domain.model.Drone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.musalasoft.drone.domain.model.DroneState.*;


@Component
@EnableScheduling
@Transactional
@Slf4j
public class DroneScheduleTask implements Task {

    private final DroneRepository repository;

    public DroneScheduleTask(DroneRepository repository) {
        this.repository = repository;
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void execute() {
        startCharging();
        delivering();
        delivered();
        returning();
        park();
    }

    public void startCharging() {
        repository.findAllByDroneState(IDLE)
                .stream()
                .peek(this::log)
                .map(Drone::charge)
                .peek(this::log)
                .forEach(repository::save);
    }

    public void delivering() {
        repository.findAllByDroneState(LOADED)
                .stream()
                .peek(this::log)
                .map(Drone::delivering)
                .peek(this::log)
                .forEach(repository::save);
    }

    public void delivered() {
        repository.findAllByDroneState(DELIVERING)
                .stream()
                .peek(this::log)
                .map(Drone::delivered)
                .peek(this::log)
                .forEach(repository::save);
    }


    public void returning() {
        repository.findAllByDroneState(DELIVERED)
                .stream()
                .peek(this::log)
                .map(Drone::returnDrone)
                .peek(this::log)
                .forEach(repository::save);
    }

    public void park() {
        repository.findAllByDroneState(RETURNING)
                .stream()
                .peek(this::log)
                .map(Drone::land)
                .peek(this::log)
                .forEach(repository::save);
    }

    private void log(Drone drone) {
        log.info("Drone status for " + drone.getSerialNumber() + " " + drone);
    }

}
