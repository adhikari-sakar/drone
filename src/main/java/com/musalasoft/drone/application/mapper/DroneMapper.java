package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.repository.DroneEntity;
import com.musalasoft.drone.model.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface DroneMapper {

    @Mapping(target = "serialNumber.id", source = "serialNumber")
    @Mapping(target = "weightLimit.limit", source = "weightLimit")
    @Mapping(target = "batteryCapacity.capacity", source = "batteryCapacity")
    Drone toModel(DroneEntity entity);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "weightLimit.limit", target = "weightLimit")
    @Mapping(source = "batteryCapacity.capacity", target = "batteryCapacity")
    DroneEntity toEntity(Drone model);

    DroneEntity toEntity(NewDroneRequest request);
}
