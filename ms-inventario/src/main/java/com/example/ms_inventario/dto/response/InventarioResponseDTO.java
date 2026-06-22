package com.example.ms_inventario.dto.response;

import com.example.ms_inventario.dto.external.ProductoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema; // <-- Importante para Swagger
import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Modelo de respuesta que representa un registro de inventario, enriquecido con soporte HATEOAS")
public class InventarioResponseDTO extends RepresentationModel<InventarioResponseDTO> {

    @Schema(description = "Identificador único del registro de inventario", example = "1")
    private Integer id;

    @Schema(description = "Código alfanumérico identificador de la bodega", example = "BOD-NORTE-01")
    private String codigoBodega;

    @Schema(description = "Nombre de la bodega asignada", example = "Bodega Central Distribución Norte")
    private String nombreBodega;

    @Schema(description = "Capacidad máxima de la unidad de almacenamiento", example = "5000")
    private Integer capacidadTotal;

    @Schema(description = "Indica si la bodega se encuentra operativa", example = "true")
    private boolean activo;

    @Schema(description = "Fecha en que se registró la apertura física o lógica", example = "2026-01-15")
    private LocalDate fechaApertura;

    @Schema(description = "Detalle del producto asociado, consultado dinámicamente desde el microservicio ms-productos")
    private ProductoDTO producto;
}