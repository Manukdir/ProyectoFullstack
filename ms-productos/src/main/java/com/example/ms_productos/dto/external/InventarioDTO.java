package com.example.ms_productos.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Representa la respuesta que entrega ms-inventario.
 * Solo se mapean los campos que ms-productos necesita; @JsonIgnoreProperties
 * evita errores si ms-inventario agrega campos nuevos en el futuro (por
 * ejemplo, los enlaces _links que agrega HATEOAS).
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventarioDTO {
    private Integer id;
    private String codigoBodega;
    private String nombreBodega;
    private Integer capacidadTotal;
    private boolean activo;
    private LocalDate fechaApertura;
}
