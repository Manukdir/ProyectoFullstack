package com.example.ms_empleados.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRequestDTO {

        @NotNull(message = "El id de sucursal es obligatorio")
        @Positive(message = "El id de sucursal debe ser positivo")
        private Integer sucursalId;
    @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe tener formato de email")
        @Size(max = 120, message = "El campo email no puede superar 120 caracteres")
        private String email;
    @NotBlank(message = "El campo cargo es obligatorio")
        @Size(min = 2, max = 150, message = "El campo cargo debe tener entre 2 y 150 caracteres")
        private String cargo;
    @NotNull(message = "El campo horasSemanales es obligatorio")
        @Min(value = 0, message = "El campo horasSemanales no puede ser negativo")
        private Integer horasSemanales;
    @NotNull(message = "El campo sueldo es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo sueldo debe ser mayor que cero")
        private BigDecimal sueldo;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaIngreso es obligatorio")
        @PastOrPresent(message = "El campo fechaIngreso no puede ser futura")
        private LocalDate fechaIngreso;
}
