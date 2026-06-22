package com.example.ms_inventario.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema; // <-- Importante para Swagger
import java.time.LocalDate;

@Data
@Schema(description = "Modelo requerido para la creación o actualización de un registro de inventario en bodega")
public class InventarioRequestDTO {

    @Schema(description = "Código identificador único de la bodega", example = "BOD-NORTE-01")
    @NotBlank(message = "El codigo de bodega es obligatorio")
    private String codigoBodega;

    @Schema(description = "Nombre descriptivo de la sucursal o espacio de almacenamiento", example = "Bodega Central Distribución Norte")
    @NotBlank(message = "El nombre de la bodega es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreBodega;

    @Schema(description = "Capacidad máxima de almacenamiento de unidades en la bodega", example = "5000")
    @NotNull(message = "La capacidad total es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidadTotal;

    @Schema(description = "Estado operativo de la bodega", example = "true")
    private boolean activo = true;

    @Schema(description = "Fecha de inauguración o apertura de la bodega", example = "2026-01-15")
    @NotNull(message = "La fecha de apertura es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaApertura;

    @Schema(description = "ID del producto que se va a almacenar, validado remotamente", example = "15")
    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productoId;
}