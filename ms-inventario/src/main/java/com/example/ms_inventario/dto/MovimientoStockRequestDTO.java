package com.example.ms_inventario.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockRequestDTO {

        @NotNull(message = "El id de inventario es obligatorio")
        @Positive(message = "El id de inventario debe ser positivo")
        private Integer inventarioId;
    @NotBlank(message = "El campo tipoMovimiento es obligatorio")
        @Size(min = 2, max = 150, message = "El campo tipoMovimiento debe tener entre 2 y 150 caracteres")
        private String tipoMovimiento;
    @NotBlank(message = "El campo motivo es obligatorio")
        @Size(min = 2, max = 150, message = "El campo motivo debe tener entre 2 y 150 caracteres")
        private String motivo;
    @NotBlank(message = "El campo documentoReferencia es obligatorio")
        @Size(min = 2, max = 150, message = "El campo documentoReferencia debe tener entre 2 y 150 caracteres")
        private String documentoReferencia;
    @NotNull(message = "El campo cantidad es obligatorio")
        @Min(value = 0, message = "El campo cantidad no puede ser negativo")
        private Integer cantidad;
    @NotNull(message = "El campo costoMovimiento es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo costoMovimiento debe ser mayor que cero")
        private BigDecimal costoMovimiento;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaMovimiento es obligatorio")
        @PastOrPresent(message = "El campo fechaMovimiento no puede ser futuro")
        private LocalDateTime fechaMovimiento;
}
