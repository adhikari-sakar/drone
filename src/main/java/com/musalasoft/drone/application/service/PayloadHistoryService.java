package com.musalasoft.drone.application.service;

import com.musalasoft.drone.application.dto.MedicationDto;
import com.musalasoft.drone.application.mapper.HistoryMapper;
import com.musalasoft.drone.domain.model.PayloadHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PayloadHistoryService {

    private final HistoryMapper mapper;

    public List<MedicationDto> medicationsDto(List<PayloadHistory> medications) {
        return mapper.toMedications(medications);
    }
}
