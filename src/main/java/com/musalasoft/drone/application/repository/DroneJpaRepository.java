package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.model.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, Long> {
    List<DroneEntity> findAllByState(DroneState state);

    Optional<DroneEntity> findBySerialNumber(String serialNumber);
}
