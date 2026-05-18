package com.example.ms_productos.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CategoriaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotBlank(message = "El código alterno es obligatorio")
    private String codigoAlterno;

    @NotNull(message = "La prioridad de visualización es obligatoria")
    @Min(value = 1, message = "La prioridad debe ser al menos 1")
    private Integer prioridadVisualizacion;

    private boolean activo = true;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaCreacion;
}