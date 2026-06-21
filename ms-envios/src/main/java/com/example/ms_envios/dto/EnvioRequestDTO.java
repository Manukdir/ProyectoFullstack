package com.example.ms_envios.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para crear o actualizar un envío")
public class EnvioRequestDTO {

    @Schema(description = "Identificador del pedido", example = "1")
        @NotNull(message = "El id de pedido es obligatorio")
        @Positive(message = "El id de pedido debe ser positivo")
        private Integer pedidoId;
    @Schema(description = "Identificador del usuario", example = "1")
    @NotNull(message = "El id de usuario es obligatorio")
        @Positive(message = "El id de usuario debe ser positivo")
        private Integer usuarioId;
    @Schema(description = "Código de seguimiento", example = "ENV-1001")
    @NotBlank(message = "El campo codigoSeguimiento es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigoSeguimiento debe tener entre 2 y 150 caracteres")
        private String codigoSeguimiento;
    @Schema(description = "Dirección de destino", example = "Av. Principal 123")
    @NotBlank(message = "El campo direccionDestino es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccionDestino debe tener entre 2 y 150 caracteres")
        private String direccionDestino;
    @Schema(description = "Empresa transportista", example = "Chilexpress")
    @NotBlank(message = "El campo transportista es obligatorio")
        @Size(min = 2, max = 150, message = "El campo transportista debe tener entre 2 y 150 caracteres")
        private String transportista;
    @Schema(description = "Costo del envío", example = "3990.00")
    @NotNull(message = "El campo costoEnvio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo costoEnvio debe ser mayor que cero")
        private BigDecimal costoEnvio;
    @Schema(description = "Indica si fue entregado", example = "false")
    @NotNull(message = "El campo entregado es obligatorio")
        private Boolean entregado;
    @Schema(description = "Fecha de despacho", example = "2026-06-20")
    @NotNull(message = "El campo fechaEnvio es obligatorio")
        @PastOrPresent(message = "El campo fechaEnvio no puede ser futura")
        private LocalDate fechaEnvio;
    @Schema(description = "Fecha estimada de entrega", example = "2027-06-25")
    @NotNull(message = "El campo fechaEstimadaEntrega es obligatorio")
        @Future(message = "El campo fechaEstimadaEntrega debe ser futura")
        private LocalDate fechaEstimadaEntrega;
}
