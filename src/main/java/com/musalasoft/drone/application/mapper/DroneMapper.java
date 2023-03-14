package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.dto.DroneDto;
import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.repository.DroneEntity;
import com.musalasoft.drone.domain.model.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface DroneMapper {

    @Mapping(target = "serialNumber.id", source = "serialNumber")
    @Mapping(target = "weight.unit", source = "weight")
    @Mapping(target = "battery.capacity", source = "battery")
    Drone toModel(DroneEntity entity);

    DroneDto toDroneDto(NewDroneRequest request);

    @Mapping(target = "serialNumber.id", source = "serialNumber")
    @Mapping(target = "weight.unit", source = "weight")
    @Mapping(target = "battery.capacity", source = "battery")
    Drone toModel(DroneDto droneDto);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "battery.capacity", target = "battery")
    DroneEntity toEntity(Drone model);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "battery.capacity", target = "battery")
    DroneDto toDto(Drone model);

    List<Drone> toDroneModels(List<DroneEntity> entities);

    List<DroneEntity> toDroneEntities(List<Drone> drones);
}
