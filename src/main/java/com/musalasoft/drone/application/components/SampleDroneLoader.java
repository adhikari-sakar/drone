package com.musalasoft.drone.application.components;

import com.musalasoft.drone.application.dto.DroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneRepository;

import javax.annotation.PostConstruct;
import java.util.List;

public class SampleDroneLoader {
    private final DroneRepository repository;
    private final List<DroneRequest> droneRequests;
    private final DroneMapper mapper;

    public SampleDroneLoader(DroneRepository repository, List<DroneRequest> droneRequests, DroneMapper mapper) {
        this.repository = repository;
        this.droneRequests = droneRequests;
        this.mapper = mapper;
    }

    @PostConstruct
    void loadDrones() {
        droneRequests.forEach(request -> repository.save(mapper.toModel(mapper.toDroneDto(request))));
    }
}
