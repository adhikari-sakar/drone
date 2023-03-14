package com.musalasoft.drone.application;

import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.contracts.Task;
import com.musalasoft.drone.domain.model.Drone;
import com.musalasoft.drone.domain.model.DroneState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        repository.saveAll(transit(IDLE, Drone::charge));
    }

    public void delivering() {
        repository.saveAll(transit(LOADED, Drone::deliver));
    }

    public void delivered() {
        repository.saveAll(transit(DELIVERING, Drone::delivered));
    }

    public void returning() {
        repository.saveAll(transit(DELIVERED, Drone::returnDrone));
    }

    public void park() {
        repository.saveAll(transit(RETURNING, Drone::land));
    }

    private List<Drone> transit(DroneState state, Function<Drone, Drone> droneFunction) {
        return repository.findAllByDroneState(state)
                .stream()
                .peek(this::log)
                .map(droneFunction)
                .peek(this::log)
                .collect(Collectors.toList());
    }

    private void log(Drone drone) {
        log.info("Drone status for {} {}", drone.getSerialNumber(), drone);
    }

}
