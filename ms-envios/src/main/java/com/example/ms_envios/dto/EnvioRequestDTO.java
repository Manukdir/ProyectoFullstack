package com.example.ms_envios.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioRequestDTO {

        @NotNull(message = "El id de pedido es obligatorio")
        @Positive(message = "El id de pedido debe ser positivo")
        private Integer pedidoId;
    @NotNull(message = "El id de usuario es obligatorio")
        @Positive(message = "El id de usuario debe ser positivo")
        private Integer usuarioId;
    @NotBlank(message = "El campo codigoSeguimiento es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigoSeguimiento debe tener entre 2 y 150 caracteres")
        private String codigoSeguimiento;
    @NotBlank(message = "El campo direccionDestino es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccionDestino debe tener entre 2 y 150 caracteres")
        private String direccionDestino;
    @NotBlank(message = "El campo transportista es obligatorio")
        @Size(min = 2, max = 150, message = "El campo transportista debe tener entre 2 y 150 caracteres")
        private String transportista;
    @NotNull(message = "El campo costoEnvio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo costoEnvio debe ser mayor que cero")
        private BigDecimal costoEnvio;
    @NotNull(message = "El campo entregado es obligatorio")
        private Boolean entregado;
    @NotNull(message = "El campo fechaEnvio es obligatorio")
        @PastOrPresent(message = "El campo fechaEnvio no puede ser futura")
        private LocalDate fechaEnvio;
    @NotNull(message = "El campo fechaEstimadaEntrega es obligatorio")
        @Future(message = "El campo fechaEstimadaEntrega debe ser futura")
        private LocalDate fechaEstimadaEntrega;
}
