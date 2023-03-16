package com.musalasoft.drone.application.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "PAYLOAD_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayloadHistoryEntity extends BaseEntity {
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(nullable = false)
    private String serialNumber;
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
