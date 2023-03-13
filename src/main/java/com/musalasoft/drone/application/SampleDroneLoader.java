package com.musalasoft.drone.application;

import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneJpaRepository;

import javax.annotation.PostConstruct;
import java.util.List;

public class SampleDroneLoader {
    private final DroneJpaRepository repository;
    private final List<NewDroneRequest> drones;
    private final DroneMapper mapper;

    public SampleDroneLoader(DroneJpaRepository repository, List<NewDroneRequest> drones, DroneMapper mapper) {
        this.repository = repository;
        this.drones = drones;
        this.mapper = mapper;
    }

    @PostConstruct
    void loadDrones() {
        drones.forEach(request -> repository.save(mapper.toEntity(request)));
    }
}
