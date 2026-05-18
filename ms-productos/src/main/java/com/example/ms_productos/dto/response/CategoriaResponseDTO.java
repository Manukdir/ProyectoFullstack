package com.example.ms_productos.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CategoriaResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String codigoAlterno;
    private Integer prioridadVisualizacion;
    private boolean activo;
    private LocalDate fechaCreacion;
}