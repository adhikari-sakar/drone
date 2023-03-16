package com.musalasoft.drone.application.mapper;

import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.repository.PayloadHistoryEntity;
import com.musalasoft.drone.domain.model.PayloadHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "serialNumber", target = "serialNumber.id")
    @Mapping(source = "name", target = "name.name")
    @Mapping(source = "weight", target = "weight.unit")
    @Mapping(source = "code", target = "code.code")
    @Mapping(source = "imageUrl", target = "image.url")
    PayloadHistory toModel(PayloadHistoryEntity entity);

    @Mapping(source = "serialNumber.id", target = "serialNumber")
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "code.code", target = "code")
    @Mapping(source = "image.url", target = "imageUrl")
    PayloadHistoryEntity toModel(PayloadHistory entity);

    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "weight.unit", target = "weight")
    @Mapping(source = "code.code", target = "code")
    @Mapping(source = "image.url", target = "imageUrl")
    MedicationDto toMedication(PayloadHistory entity);

    List<MedicationDto> toMedications(List<PayloadHistory> medications);
}
