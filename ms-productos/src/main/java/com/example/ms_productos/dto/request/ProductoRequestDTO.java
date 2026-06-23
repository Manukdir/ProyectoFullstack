package com.example.ms_productos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(
        name = "ProductoRequest",
        description = "Modelo de datos (Request Body) requerido para la creación o actualización de un producto en el inventario"
)
public class ProductoRequestDTO {

    @Schema(
            description = "Nombre comercial descriptivo del producto.",
            example = "Teclado Mecánico RGB Corsair",
            minLength = 2,
            maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "el nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Schema(
            description = "Código SKU (Stock Keeping Unit) único asignado para el control logístico del producto.",
            example = "SKU-TEC-COR-01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El SKU es obligatorio")
    private String codigoSku;

    @Schema(
            description = "Precio unitario de venta. Debe ser un valor estrictamente mayor a 0.",
            example = "89990.00",
            minimum = "0.01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @Schema(
            description = "Cantidad física total de unidades disponibles en bodega. No admite valores negativos.",
            example = "45",
            minimum = "0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Schema(
            description = "Fecha formal en la que ingresa el lote del producto al sistema. No puede ser una fecha futura.",
            type = "string",
            format = "date",
            example = "2026-06-22",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "la fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaIngreso;

    @Schema(
            description = "Flag indicador que define si el producto se encuentra visible para la venta en el catálogo público.",
            example = "true",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            defaultValue = "true"
    )
    private boolean disponible = true;

    @Schema(
            description = "Identificador numérico (ID) de la categoría a la cual se asociará este producto. Debe existir en el sistema.",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "el ID de la categoría es obligatorio")
    private Integer categoriaId;
}