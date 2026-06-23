package com.example.ms_productos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data


@Schema(
        name = "CategoriaRequest",
        description = "Modelo de datos (Request Body) requerido para registrar o actualizar una categoría de productos en el sistema"
)
public class CategoriaRequestDTO {

    @Schema(
            description = "Nombre comercial e identitario de la categoría. Debe ser único.",
            example = "Tecnología y Electrónica",
            minLength = 2,
            maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Schema(
            description = "Breve reseña sobre el tipo de productos que agrupará esta categoría.",
            example = "Dispositivos móviles, computadores, repuestos y accesorios tecnológicos de última generación.",
            minLength = 5,
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @Schema(
            description = "Código alfanumérico único para homologación con sistemas externos de inventario o ERP.",
            example = "CAT-TEC-2026",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El código alterno es obligatorio")
    private String codigoAlterno;

    @Schema(
            description = "Valor numérico para determinar el orden jerárquico de visualización en la interfaz del cliente. Valores menores se muestran primero.",
            example = "1",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La prioridad de visualización es obligatoria")
    @Min(value = 1, message = "La prioridad debe ser al menos 1")
    private Integer prioridadVisualizacion;

    @Schema(
            description = "Define si la categoría está disponible para ser vinculada a nuevos productos.",
            example = "true",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            defaultValue = "true"
    )
    private boolean activo = true;

    @Schema(
            description = "Fecha formal en la que se da de alta el registro. No puede ser una fecha futura.",
            example = "2026-06-22",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaCreacion;
}