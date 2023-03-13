package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.repository.MedicationEntity;
import com.musalasoft.drone.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    @Mapping(target = "name.name", source = "name")
    @Mapping(target = "weight.weight", source = "weight")
    @Mapping(target = "code.code", source = "code")
    @Mapping(target = "image.url", source = "imageUrl")
    Medication toModel(MedicationEntity entity);

    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "weight.weight", target = "weight")
    @Mapping(source = "code.code", target = "code")
    @Mapping(source = "image.url", target = "imageUrl")
    MedicationEntity toEntity(Medication model);
}
