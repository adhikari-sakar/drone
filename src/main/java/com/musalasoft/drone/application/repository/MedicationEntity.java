package com.musalasoft.drone.application.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "MEDICATION")
@Getter
@Setter
@NoArgsConstructor
public class MedicationEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @NotNull
    @Max(500)
    @Min(1)
    @Column(nullable = false)
    private Double weight;
    @NotBlank
    @Column(nullable = false)
    private String code;
    @NotBlank
    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DroneEntity drone;
}
