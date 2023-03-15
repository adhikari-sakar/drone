package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.domain.contracts.BaseRepository;
import com.musalasoft.drone.domain.model.Drone;
import com.musalasoft.drone.domain.model.DroneState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DroneRepository implements BaseRepository<Drone> {
    private final DroneMapper mapper;
    private final DroneJpaRepository jpaRepository;

    @Override
    public Drone save(Drone model) {
        return mapper.toModel(jpaRepository.save(mapper.toEntity(model)));
    }

    public List<Drone> findAllByDroneState(DroneState state) {
        return mapper.toDroneModels(jpaRepository.findAllByState(state));
    }

    public Optional<Drone> findBySerialNumber(String serialNumber) {
        return jpaRepository.findBySerialNumber(serialNumber).map(mapper::toModel);
    }

    public void saveAll(List<Drone> drones) {
        mapper.toDroneModels(jpaRepository.saveAll(mapper.toDroneEntities(drones)));
    }

    public List<Drone> findAll() {
        return mapper.toDroneModels(jpaRepository.findAll());
    }
}
