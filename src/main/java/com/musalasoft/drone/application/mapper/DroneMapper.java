package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.dto.DroneDto;
import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.repository.DroneEntity;
import com.musalasoft.drone.model.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface DroneMapper {

    @Mapping(target = "serialNumber.id", source = "serialNumber")
    @Mapping(target = "weight.unit", source = "weightLimit")
    @Mapping(target = "battery.capacity", source = "batteryCapacity")
    Drone toModel(DroneEntity entity);

    DroneDto toDroneDto(NewDroneRequest request);

    @Mapping(target = "serialNumber.id", source = "serialNumber")
    @Mapping(target = "weight.unit", source = "weightLimit")
    @Mapping(target = "battery.capacity", source = "batteryCapacity")
    Drone toModel(DroneDto droneDto);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "weight.unit", target = "weightLimit")
    @Mapping(source = "battery.capacity", target = "batteryCapacity")
    DroneEntity toEntity(Drone model);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "weight.unit", target = "weightLimit")
    @Mapping(source = "battery.capacity", target = "batteryCapacity")
    DroneDto toDto(Drone model);

    List<Drone> toDroneList(List<DroneEntity> entities);
}
