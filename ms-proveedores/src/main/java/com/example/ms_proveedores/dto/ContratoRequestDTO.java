package com.example.ms_proveedores.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoRequestDTO {

        @NotNull(message = "El id de proveedor es obligatorio")
        @Positive(message = "El id de proveedor debe ser positivo")
        private Integer proveedorId;
    @NotBlank(message = "El campo codigoContrato es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigoContrato debe tener entre 2 y 150 caracteres")
        private String codigoContrato;
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @NotNull(message = "El campo duracionMeses es obligatorio")
        @Min(value = 0, message = "El campo duracionMeses no puede ser negativo")
        private Integer duracionMeses;
    @NotNull(message = "El campo montoMensual es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo montoMensual debe ser mayor que cero")
        private BigDecimal montoMensual;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaInicio es obligatorio")
        @PastOrPresent(message = "El campo fechaInicio no puede ser futura")
        private LocalDate fechaInicio;
    @NotNull(message = "El campo fechaTermino es obligatorio")
        @Future(message = "El campo fechaTermino debe ser futura")
        private LocalDate fechaTermino;
}
