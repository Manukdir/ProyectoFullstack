package com.example.ms_reportes.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRequestDTO {

        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo tipoReporte es obligatorio")
        @Size(min = 2, max = 150, message = "El campo tipoReporte debe tener entre 2 y 150 caracteres")
        private String tipoReporte;
    @NotBlank(message = "El campo resumen es obligatorio")
        @Size(min = 2, max = 150, message = "El campo resumen debe tener entre 2 y 150 caracteres")
        private String resumen;
    @NotNull(message = "El campo cantidadPedidos es obligatorio")
        @Min(value = 0, message = "El campo cantidadPedidos no puede ser negativo")
        private Integer cantidadPedidos;
    @NotNull(message = "El campo totalVentas es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo totalVentas debe ser mayor que cero")
        private BigDecimal totalVentas;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaGeneracion es obligatorio")
        @PastOrPresent(message = "El campo fechaGeneracion no puede ser futuro")
        private LocalDateTime fechaGeneracion;
}
