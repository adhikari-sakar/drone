package com.musalasoft.drone.application.components;

import com.musalasoft.drone.application.dto.ErrorResponse;
import com.musalasoft.drone.application.exception.DroneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(DroneException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST, e.getMessage()));
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
