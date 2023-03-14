package com.musalasoft.drone.application.controller;

import com.musalasoft.drone.application.dto.DroneDto;
import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/drones/")
public class DroneController {

    private final DroneService droneService;
    private final DroneMapper mapper;

    public DroneController(DroneService droneService, DroneMapper mapper) {
        this.droneService = droneService;
        this.mapper = mapper;
    }

    @PostMapping(path = "register", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ResponseEntity<DroneDto> register(@RequestBody @Valid NewDroneRequest request) {
        return ResponseEntity.ok(droneService.registerNewDrone(mapper.toDroneDto(request)));
    }

    @PostMapping(path = "/medications/load/{serialNumber}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ResponseEntity<DroneDto> addMedication(@PathVariable String serialNumber, @RequestBody @Valid List<MedicationDto> medicationRequests) {
        return ResponseEntity.ok(droneService.loadMedications(serialNumber, medicationRequests));
    }

    @GetMapping(path = "/medications/fetch/{serialNumber}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<MedicationDto>> medication(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.findMedications(serialNumber));
    }

    @GetMapping(path = "/battery/{serialNumber}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<Double> batteryLevel(@PathVariable String serialNumber) {
        return ResponseEntity.ok(droneService.batteryLevel(serialNumber));
    }


    @GetMapping(path = "availableDrones", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<DroneDto>> availableDrones() {
        return ResponseEntity.ok(droneService.availableDrones());
    }

}
