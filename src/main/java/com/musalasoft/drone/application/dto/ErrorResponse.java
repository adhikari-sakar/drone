package com.musalasoft.drone.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

@JsonComponent
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String errorMessage;
}
