package com.example.ms_inventario.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InventarioResponseDTO {
    private Integer id;
    private String codigoBodega;
    private String nombreBodega;
    private Integer capacidadTotal;
    private boolean activo;
    private LocalDate fechaApertura;
}