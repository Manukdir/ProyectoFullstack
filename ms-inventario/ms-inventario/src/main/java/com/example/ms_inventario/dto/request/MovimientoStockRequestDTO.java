package com.example.ms_inventario.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MovimientoStockRequestDTO {

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;

    @NotBlank(message = "El motivo es obligatorio")
    @Size(min = 5, max = 200, message = "El motivo debe tener entre 5 y 200 caracteres")
    private String motivo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    private boolean aprobado = true;

    @NotNull(message = "La fecha de movimiento es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaMovimiento;

    @NotNull(message = "El ID del inventario es obligatorio")
    private Integer inventarioId;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productoId;
}