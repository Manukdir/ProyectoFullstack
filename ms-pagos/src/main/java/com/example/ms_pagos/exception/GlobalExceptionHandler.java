package com.example.ms_pagos.exception;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Convierte las excepciones en respuestas JSON con códigos HTTP claros.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validacion(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Los datos enviados no son válidos",
                request.getRequestURI(), errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> noEncontrado(
            ResourceNotFoundException exception,
            HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> conflicto(
            DataIntegrityViolationException exception,
            HttpServletRequest request) {
        log.warn("Conflicto con los datos", exception);
        return build(HttpStatus.CONFLICT, "La operación genera un conflicto con los datos",
                request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiError> errorFeign(
            FeignException exception,
            HttpServletRequest request) {
        log.error("Error al comunicarse con pedidos", exception);
        return build(HttpStatus.BAD_GATEWAY, "No fue posible comunicarse con pedidos",
                request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> errorGeneral(
            Exception exception,
            HttpServletRequest request) {
        log.error("Error inesperado", exception);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado",
                request.getRequestURI(), Map.of());
    }

    private ResponseEntity<ApiError> build(
            HttpStatus status,
            String message,
            String path,
            Map<String, String> errors) {
        ApiError body = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                errors);
        return ResponseEntity.status(status).body(body);
    }
}
