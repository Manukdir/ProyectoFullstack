package com.example.ms_productos.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

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

    private Integer categoriaId;
    private String categoriaNombre;
}