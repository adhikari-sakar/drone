package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.domain.model.DroneModel;
import com.musalasoft.drone.domain.model.DroneState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class DroneJpaRepositoryTest {

    @Autowired
    private DroneJpaRepository jpaRepository;

    @BeforeEach
    void save() {
        jpaRepository.save(drone());
    }

    @Test
    void findById() {
        assertNotNull(jpaRepository.findById(1L));
    }

    @Test
    void findMedications() {
        Optional<DroneEntity> drone = jpaRepository.findById(1L);
        assertTrue(drone.isPresent());
        assertNotNull(drone.get().getMedications());
    }

    private DroneEntity drone() {
        var drone = new DroneEntity();
        drone.setSerialNumber(UUID.randomUUID().toString());
        drone.setModel(DroneModel.LIGHT_WEIGHT);
        drone.setWeight(100.00);
        drone.setBattery(100.00);
        drone.setState(DroneState.IDLE);
        drone.setMedications(List.of(medication()));
        return drone;
    }


    private MedicationEntity medication() {
        var medication = new MedicationEntity();
        medication.setName("Test");
        medication.setWeight(20.00);
        medication.setCode("Test001");
        medication.setImageUrl("/src/image/test.png");
        return medication;
    }

}