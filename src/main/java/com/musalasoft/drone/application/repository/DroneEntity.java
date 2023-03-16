package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.domain.model.DroneModel;
import com.musalasoft.drone.domain.model.DroneState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

@Entity(name = "DRONE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity extends BaseEntity {

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(STRING)
    @Column(nullable = false)
    private DroneModel model;
    @Max(500)
    @Min(1)
    private Double weight;

    @Max(100)
    @Min(0)
    @Column(nullable = false)
    private Double battery;
    @Enumerated(STRING)
    @Column(nullable = false)
    private DroneState state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "drone", orphanRemoval = true)
    private List<MedicationEntity> medications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drone")
    private List<PayloadHistoryEntity> histories;

    public void setMedications(List<MedicationEntity> medications) {
        this.medications = Objects.requireNonNullElseGet(medications, ArrayList::new);
        this.medications.forEach(medication -> medication.setDrone(this));
    }

    public void setHistories(List<PayloadHistoryEntity> histories) {
        this.histories = Objects.requireNonNullElseGet(histories, ArrayList::new);
        this.histories.forEach(medication -> medication.setDrone(this));
    }
}
