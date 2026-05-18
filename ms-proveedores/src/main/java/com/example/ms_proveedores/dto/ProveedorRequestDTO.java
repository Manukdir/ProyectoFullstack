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
public class ProveedorRequestDTO {

        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo rut es obligatorio")
        @Size(min = 2, max = 150, message = "El campo rut debe tener entre 2 y 150 caracteres")
        private String rut;
    @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe tener formato de email")
        @Size(max = 120, message = "El campo email no puede superar 120 caracteres")
        private String email;
    @NotNull(message = "El campo calificacion es obligatorio")
        @Min(value = 0, message = "El campo calificacion no puede ser negativo")
        private Integer calificacion;
    @NotNull(message = "El campo montoCompras es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo montoCompras debe ser mayor que cero")
        private BigDecimal montoCompras;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaRegistro es obligatorio")
        @PastOrPresent(message = "El campo fechaRegistro no puede ser futura")
        private LocalDate fechaRegistro;
}
