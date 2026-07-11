package com.example.ms_envios.exception;

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
 * Transforma excepciones en respuestas JSON con el código HTTP correspondiente.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request, Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> details = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Existen campos inválidos", request, details);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        log.error("Conflicto con los datos almacenados", ex);
        return build(HttpStatus.CONFLICT, "La operación genera un conflicto de datos", request, Map.of());
    }

    @ExceptionHandler(RemoteServiceException.class)
    public ResponseEntity<ApiError> handleRemoteService(RemoteServiceException ex, HttpServletRequest request) {
        log.error("Error al validar datos remotos", ex);
        return build(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), request, Map.of());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiError> handleFeign(FeignException ex, HttpServletRequest request) {
        log.error("Error al comunicarse con otro microservicio", ex);
        HttpStatus status = mapFeignStatus(ex.status());
        return build(status, feignMessage(status), request, Map.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex, HttpServletRequest request) {
        log.error("Error inesperado", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno", request, Map.of());
    }

    private ResponseEntity<ApiError> build(
            HttpStatus status, String message, HttpServletRequest request,
            Map<String, String> details) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                details);
        return ResponseEntity.status(status).body(error);
    }

    private HttpStatus mapFeignStatus(int status) {
        if (status == HttpStatus.NOT_FOUND.value()) {
            return HttpStatus.NOT_FOUND;
        }
        if (status == -1
                || status == HttpStatus.REQUEST_TIMEOUT.value()
                || status == HttpStatus.SERVICE_UNAVAILABLE.value()
                || status == HttpStatus.GATEWAY_TIMEOUT.value()) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
        return HttpStatus.BAD_GATEWAY;
    }

    private String feignMessage(HttpStatus status) {
        if (status == HttpStatus.NOT_FOUND) {
            return "El recurso remoto solicitado no existe";
        }
        if (status == HttpStatus.SERVICE_UNAVAILABLE) {
            return "El microservicio remoto no esta disponible";
        }
        return "No fue posible comunicarse con el microservicio remoto";
    }
}
