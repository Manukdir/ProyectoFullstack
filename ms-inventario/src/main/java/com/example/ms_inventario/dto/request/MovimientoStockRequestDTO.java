package com.example.ms_inventario.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema; // <-- Importante para Swagger
import java.time.LocalDate;

@Data
@Schema(description = "Modelo requerido para registrar un nuevo movimiento de stock (Ingreso/Egreso)")
public class MovimientoStockRequestDTO {

    @Schema(description = "Tipo de operación en el inventario", example = "INGRESO", allowableValues = {"INGRESO", "EGRESO"})
    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;

    @Schema(description = "Justificación detallada del movimiento", example = "Compra de insumos y reabastecimiento de stock")
    @NotBlank(message = "El motivo es obligatorio")
    @Size(min = 5, max = 200, message = "El motivo debe tener entre 5 y 200 caracteres")
    private String motivo;

    @Schema(description = "Cantidad de unidades involucradas en el movimiento", example = "50")
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @Schema(description = "Estado de aprobación del movimiento", example = "true")
    private boolean aprobado = true;

    @Schema(description = "Fecha en la que se ejecuta la operación", example = "2026-06-21")
    @NotNull(message = "La fecha de movimiento es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaMovimiento;

    @Schema(description = "ID del inventario afectado", example = "1")
    @NotNull(message = "El ID del inventario es obligatorio")
    private Integer inventarioId;

    @Schema(description = "ID del producto consultado mediante OpenFeign", example = "15")
    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productoId;
}