package com.example.ms_productos.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {

        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @NotNull(message = "El campo prioridad es obligatorio")
        @Min(value = 0, message = "El campo prioridad no puede ser negativo")
        private Integer prioridad;
    @NotNull(message = "El campo margenGanancia es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo margenGanancia debe ser mayor que cero")
        private BigDecimal margenGanancia;
    @NotNull(message = "El campo activa es obligatorio")
        private Boolean activa;
    @NotNull(message = "El campo fechaCreacion es obligatorio")
        @PastOrPresent(message = "El campo fechaCreacion no puede ser futura")
        private LocalDate fechaCreacion;
}
