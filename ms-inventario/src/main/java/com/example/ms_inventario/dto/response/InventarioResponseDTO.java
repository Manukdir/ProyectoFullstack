package com.example.ms_inventario.dto.response;

import com.example.ms_inventario.dto.external.ProductoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

@Data

@EqualsAndHashCode(callSuper = true)
public class InventarioResponseDTO extends RepresentationModel<InventarioResponseDTO> {
    private Integer id;
    private String codigoBodega;
    private String nombreBodega;
    private Integer capacidadTotal;
    private boolean activo;
    private LocalDate fechaApertura;
    private ProductoDTO producto;
}