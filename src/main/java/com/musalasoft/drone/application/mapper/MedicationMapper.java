package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.repository.MedicationEntity;
import com.musalasoft.drone.domain.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    @Mapping(target = "name.name", source = "name")
    @Mapping(target = "weight.unit", source = "weight")
    @Mapping(target = "code.code", source = "code")
    @Mapping(target = "image.url", source = "imageUrl")
    Medication toModel(MedicationEntity entity);

    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "code.code", target = "code")
    @Mapping(source = "image.url", target = "imageUrl")
    MedicationEntity toEntity(Medication model);
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "code.code", target = "code")
    @Mapping(source = "image.url", target = "imageUrl")
    MedicationDto toDto(Medication model);

    @Mapping(target = "name.name", source = "name")
    @Mapping(target = "weight.unit", source = "weight")
    @Mapping(target = "code.code", source = "code")
    @Mapping(target = "image.url", source = "imageUrl")
    Medication toModel(MedicationDto dto);

    List<Medication> toMedicationsModel(List<MedicationDto> medications);
    List<MedicationDto> toMedicationsDto(List<Medication> medications);
}
