package com.example.ms_productos.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

/**
 * Extiende RepresentationModel para poder agregarle enlaces HATEOAS
 * (_links) en el Controller sin tocar los campos del DTO.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductoResponseDTO extends RepresentationModel<ProductoResponseDTO> {
    private Integer id;
    private String nombre;
    private String codigoSku;
    private Double precio;
    private Integer stock;
    private LocalDate fechaIngreso;
    private boolean disponible;

    // Datos basicos de la categoria asociada (evitamos exponer la entidad completa)
    private Integer categoriaId;
    private String categoriaNombre;
}
