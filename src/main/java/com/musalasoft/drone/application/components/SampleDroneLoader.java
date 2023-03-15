package com.musalasoft.drone.application.components;

import com.musalasoft.drone.application.dto.DroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneRepository;
import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.List;

@AllArgsConstructor
public class SampleDroneLoader {
    private final DroneRepository repository;
    private final List<DroneRequest> droneRequests;
    private final DroneMapper mapper;

    @PostConstruct
    void loadDrones() {
        droneRequests.forEach(request -> repository.save(mapper.toModel(mapper.toDroneDto(request))));
    }
}
