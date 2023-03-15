package com.musalasoft.drone.application.service;

import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.dto.MedicationRequest;
import com.musalasoft.drone.application.mapper.MedicationMapper;
import com.musalasoft.drone.domain.model.Medication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicationService {

    private final MedicationMapper mapper;

    public MedicationService(MedicationMapper mapper) {
        this.mapper = mapper;
    }

    public List<Medication> medicationsModel(List<MedicationRequest> medications) {
        return mapper.dtoToModelList(mapper.requestToDtoList(medications));
    }

    public List<MedicationDto> medicationsDto(List<Medication> medications) {
        return mapper.modelToDtoList(medications);
    }
}
