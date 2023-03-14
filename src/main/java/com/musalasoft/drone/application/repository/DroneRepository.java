package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.domain.contracts.BaseRepository;
import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public List<Drone> findAllByDroneState(DroneState state) {
        return mapper.toDroneList(jpaRepository.findAllByState(state));
    }

    public Optional<Drone> findBySerialNumber(String serialNumber) {
        return jpaRepository.findBySerialNumber(serialNumber).map(mapper::toModel);
    }
}
