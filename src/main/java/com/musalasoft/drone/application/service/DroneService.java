package com.musalasoft.drone.application.service;

import com.musalasoft.drone.application.dto.DroneDto;
import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.dto.MedicationRequest;
import com.musalasoft.drone.application.exception.DroneNotFoundException;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.model.Battery;
import com.musalasoft.drone.domain.model.Drone;
import com.musalasoft.drone.domain.model.DroneState;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DroneService {

    private final DroneRepository repository;
    private final DroneMapper mapper;
    private final MedicationService medicationService;

    public DroneService(DroneRepository repository, DroneMapper mapper, MedicationService medicationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicationService = medicationService;
    }

    public DroneDto registerNewDrone(DroneDto droneDto) {
        return mapper.toDto(repository.save(mapper.toModel(droneDto)));
    }

    public DroneDto loadMedications(String serialNumber, List<MedicationRequest> medicationRequests) {
        return repository.findBySerialNumber(serialNumber)
                .map(drone -> drone.loadItems(medicationService.medicationsModel(medicationRequests)))
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> droneNotFound(serialNumber));
    }

    public List<MedicationDto> findMedications(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .map(Drone::getMedications)
                .map(medicationService::medicationsDto)
                .orElseThrow(() -> droneNotFound(serialNumber));
    }

    public List<DroneDto> availableDrones() {
        return repository.findAllByDroneState(DroneState.IDLE)
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    public Double batteryLevel(String serialNumber) {
        return repository.findBySerialNumber(serialNumber)
                .map(Drone::getBattery)
                .map(Battery::getCapacity)
                .orElseThrow(() -> droneNotFound(serialNumber));
    }

    private static DroneNotFoundException droneNotFound(String serialNumber) {
        return new DroneNotFoundException("Drone not found for serial no: " + serialNumber);
    }
}
