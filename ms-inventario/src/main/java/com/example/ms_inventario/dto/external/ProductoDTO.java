package com.example.ms_inventario.dto.external;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema; // <-- Importante para Swagger
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO externo que representa la información de un Producto importado desde ms-productos")
public class ProductoDTO {

    @Schema(description = "Identificador único del producto", example = "1")
    private Integer id;

    @Schema(description = "Nombre comercial del producto", example = "Teclado Mecánico RGB")
    private String nombre;

    @Schema(description = "Código único de inventario (SKU)", example = "PROD-TEC-1234")
    private String codigoSku;

    @Schema(description = "Precio de venta unitario", example = "45090.00")
    private Double precio;

    @Schema(description = "Cantidad actual en stock global", example = "25")
    private Integer stock;

    @Schema(description = "Fecha en que se registró el producto", example = "2026-06-21")
    private LocalDate fechaIngreso;

    @Schema(description = "Indica si el producto está habilitado para la venta", example = "true")
    private boolean disponible;
}