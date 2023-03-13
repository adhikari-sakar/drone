package com.musalasoft.drone.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, Long> {
}
