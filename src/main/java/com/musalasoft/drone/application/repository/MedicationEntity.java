package com.musalasoft.drone.application.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "MEDICATION")
@Getter
@Setter
@NoArgsConstructor
public class MedicationEntity extends BaseEntity {
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Min(1)
    @Column(nullable = false)
    private Double weight;
    @NotBlank
    @Column(nullable = false)
    private String code;
    @NotBlank
    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private DroneEntity drone;
}
