package com.example.ms_sucursales.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionRequestDTO {

        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo codigo es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigo debe tener entre 2 y 150 caracteres")
        private String codigo;
    @NotNull(message = "El campo cantidadComunas es obligatorio")
        @Min(value = 0, message = "El campo cantidadComunas no puede ser negativo")
        private Integer cantidadComunas;
    @NotNull(message = "El campo costoDespachoBase es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo costoDespachoBase debe ser mayor que cero")
        private BigDecimal costoDespachoBase;
    @NotNull(message = "El campo activa es obligatorio")
        private Boolean activa;
    @NotNull(message = "El campo fechaCreacion es obligatorio")
        @PastOrPresent(message = "El campo fechaCreacion no puede ser futura")
        private LocalDate fechaCreacion;
}
