package com.musalasoft.drone.application.components;

import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.contracts.Task;
import com.musalasoft.drone.domain.model.Drone;
import com.musalasoft.drone.domain.model.DroneState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.musalasoft.drone.domain.model.DroneState.*;


@Component
@EnableScheduling
@Slf4j
@AllArgsConstructor
public class DroneScheduleTask implements Task {

    private final DroneRepository repository;

    @Override
    @Scheduled(fixedDelayString = "${drone.schedular.delayMs}")
    @Transactional
    public void execute() {
        charge();
        readyDrones();
        delivering();
        delivered();
        returning();
        park();
    }

    public void charge() {
        repository.saveAll(transit(IDLE, Drone::charge, "Drone is Idle. Now Charging..."));
    }

    public void readyDrones() {
        repository.saveAll(transit(LOADING, Drone::charge, "Drone is ready to load."));
    }

    public void delivering() {
        repository.saveAll(transit(LOADED, Drone::deliver, "Drone is loaded, Initiating delivery..."));
    }

    public void delivered() {
        repository.saveAll(transit(DELIVERING, Drone::delivered, "Payload is being delivered."));
    }

    public void returning() {
        repository.saveAll(transit(DELIVERED, Drone::returnDrone, "Payload is delivered."));
    }

    public void park() {
        repository.saveAll(transit(RETURNING, Drone::land, "Drone is returned."));
    }

    private List<Drone> transit(DroneState state, Function<Drone, Drone> droneFunction, String status) {
        return repository.findAllByDroneState(state)
                .stream()
                .peek(drone -> log(drone, status))
                .map(droneFunction)
                .collect(Collectors.toList());
    }

    private void log(Drone drone, String status) {
        log.info("\n {} \n Data: {} ", status, drone);
    }

}
