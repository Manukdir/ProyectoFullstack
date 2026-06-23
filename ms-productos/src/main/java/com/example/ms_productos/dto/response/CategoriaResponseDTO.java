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
public class CategoriaResponseDTO extends RepresentationModel<CategoriaResponseDTO> {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String codigoAlterno;
    private Integer prioridadVisualizacion;
    private boolean activo;
    private LocalDate fechaCreacion;
}
