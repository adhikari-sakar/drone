package com.musalasoft.drone.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Value
@Builder
public class SerialNumber {
    @NotBlank
    @Length(min = 1, max = 100)
    String id;
}
