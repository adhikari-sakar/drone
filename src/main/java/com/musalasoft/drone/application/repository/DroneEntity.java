package com.musalasoft.drone.application.repository;

import com.musalasoft.drone.model.DroneModel;
import com.musalasoft.drone.model.DroneState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity(name = "DRONE")
@Getter
@Setter
@NoArgsConstructor
public class DroneEntity extends BaseEntity {
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(STRING)
    private DroneModel model;
    @NotNull
    @Max(500)
    @Min(1)
    private Double weightLimit;

    @NotNull
    @Max(100)
    @Min(0)
    private Double batteryCapacity;
    @Enumerated(STRING)
    private DroneState state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "drone", orphanRemoval = true)
    private List<MedicationEntity> medications;
}
