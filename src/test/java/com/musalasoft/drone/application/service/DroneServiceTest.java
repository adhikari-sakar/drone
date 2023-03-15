package com.musalasoft.drone.application.service;

import com.musalasoft.drone.application.dto.DroneDto;
import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.application.exception.DroneException;
import com.musalasoft.drone.domain.model.Battery;
import com.musalasoft.drone.domain.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.musalasoft.drone.domain.model.DroneState.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DroneServiceTest {

    @Mock
    private DroneRepository repository;
    @Mock
    private DroneMapper droneMapper;
    @Mock
    private MedicationService medicationService;
    @Mock
    private Drone drone;

    private DroneService service;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(droneMapper.toModel(any(DroneDto.class))).thenReturn(drone);
        when(droneMapper.toDto(any(Drone.class))).thenReturn(new DroneDto());
        when(repository.save(any(Drone.class))).thenReturn(drone);
        service = new DroneService(repository, droneMapper, medicationService);
    }

    @Test
    void registerNewDrone() {

        assertNotNull(service.registerNewDrone(new DroneDto()));

        verify(droneMapper).toModel(any(DroneDto.class));
        verify(droneMapper).toDto(any(Drone.class));
        verify(repository).save(any(Drone.class));
    }

    @Test
    void loadMedications() {
        when(repository.findBySerialNumber("123")).thenReturn(Optional.of(drone));
        when(medicationService.medicationsModel(any())).thenReturn(List.of());
        when(drone.loadItems(any())).thenReturn(drone);

        assertNotNull(service.loadMedications("123", List.of()));

        verify(repository).findBySerialNumber("123");
        verify(medicationService).medicationsModel(any());
        verify(droneMapper).toDto(any(Drone.class));
        verify(repository).save(any(Drone.class));
    }

    @Test
    void loadMedications_droneNotFound() {
        when(repository.findBySerialNumber("123")).thenReturn(Optional.empty());
        assertThrows(DroneException.class, () -> service.loadMedications("123", List.of()));
        verify(repository).findBySerialNumber("123");
    }

    @Test
    void availableDrones() {
        when(repository.findAllByDroneState(IDLE)).thenReturn(List.of(drone));

        assertFalse(service.availableDrones().isEmpty());

        verify(repository).findAllByDroneState(IDLE);
        verify(droneMapper).toDto(any(Drone.class));
    }

    @Test
    void availableDrones_droneNotFound() {
        when(repository.findAllByDroneState(IDLE)).thenReturn(List.of());
        assertTrue(service.availableDrones().isEmpty());
        verify(repository).findAllByDroneState(IDLE);
    }

    @Test
    void findMedications() {
        when(repository.findBySerialNumber("123")).thenReturn(Optional.of(drone));
        when(drone.getMedications()).thenReturn(List.of());
        when(medicationService.medicationsDto(any())).thenReturn(List.of(new MedicationDto()));

        assertFalse(service.findMedications("123").isEmpty());

        verify(repository).findBySerialNumber("123");
        verify(medicationService).medicationsDto(any());
    }

    @Test
    void findMedications_droneNotFound() {
        when(repository.findBySerialNumber("123")).thenReturn(Optional.empty());
        assertThrows(DroneException.class, () -> service.findMedications("123"));
        verify(repository).findBySerialNumber("123");
    }

    @Test
    void batteryLevel() {
        when(drone.getBattery()).thenReturn(Battery.builder().capacity(25.00).build());
        when(repository.findBySerialNumber("123")).thenReturn(Optional.of(drone));
        assertEquals(25.00, service.batteryLevel("123"));
        verify(repository).findBySerialNumber("123");
    }

    @Test
    void batteryLevel_droneNotFound() {
        when(repository.findBySerialNumber("123")).thenReturn(Optional.of(drone));
        assertThrows(DroneException.class, () -> service.batteryLevel("123"));
        verify(repository).findBySerialNumber("123");
    }
}