package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.domain.contracts.BaseRepository;
import com.musalasoft.drone.domain.model.Drone;
import com.musalasoft.drone.domain.model.DroneState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DroneRepository implements BaseRepository<Drone> {

    private final DroneMapper mapper;
    private final Integer maxDroneNumber;
    private final DroneJpaRepository jpaRepository;

    public DroneRepository(DroneMapper mapper, @Value("${drone.max.number}") Integer maxDroneNumber, DroneJpaRepository jpaRepository) {
        this.mapper = mapper;
        this.maxDroneNumber = maxDroneNumber;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Drone save(Drone model) {
        if (jpaRepository.findAll().size() > maxDroneNumber)
            throw new RuntimeException("Failed to register a new drone. Drone limit is " + maxDroneNumber);
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
}
