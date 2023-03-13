package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.domain.contracts.BaseRepository;
import com.musalasoft.drone.model.Drone;
import org.springframework.stereotype.Component;

@Component
public class DroneRepository implements BaseRepository<Drone> {

    private final DroneMapper mapper;
    private final DroneJpaRepository jpaRepository;

    public DroneRepository(DroneMapper mapper, DroneJpaRepository jpaRepository) {
        this.mapper = mapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Drone save(Drone model) {
        return mapper.toModel(jpaRepository.save(mapper.toEntity(model)));
    }
}
