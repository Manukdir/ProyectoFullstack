package com.example.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequestDTO {

    @Schema(example = "1")
    @NotNull(message = "El id de pedido es obligatorio")
    @Positive(message = "El id de pedido debe ser positivo")
    private Integer pedidoId;

    @Schema(example = "TARJETA")
    @NotBlank(message = "El campo metodoPago es obligatorio")
    @Size(min = 2, max = 150, message = "El campo metodoPago debe tener entre 2 y 150 caracteres")
    private String metodoPago;

    @Schema(example = "APROBADO")
    @NotBlank(message = "El campo estadoPago es obligatorio")
    @Size(min = 2, max = 150, message = "El campo estadoPago debe tener entre 2 y 150 caracteres")
    private String estadoPago;

    @Schema(example = "TRX-1001")
    @NotBlank(message = "El campo codigoTransaccion es obligatorio")
    @Size(min = 2, max = 150, message = "El campo codigoTransaccion debe tener entre 2 y 150 caracteres")
    private String codigoTransaccion;

    @Schema(example = "3")
    @NotNull(message = "El campo numeroCuotas es obligatorio")
    @Min(value = 0, message = "El campo numeroCuotas no puede ser negativo")
    private Integer numeroCuotas;

    @Schema(example = "45990")
    @NotNull(message = "El campo monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El campo monto debe ser mayor que cero")
    private BigDecimal monto;

    @Schema(example = "true")
    @NotNull(message = "El campo pagado es obligatorio")
    private Boolean pagado;

    @Schema(example = "2026-06-20T10:30:00")
    @NotNull(message = "El campo fechaPago es obligatorio")
    @PastOrPresent(message = "El campo fechaPago no puede ser futuro")
    private LocalDateTime fechaPago;
}
