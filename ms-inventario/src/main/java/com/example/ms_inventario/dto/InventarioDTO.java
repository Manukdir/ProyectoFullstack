package com.example.ms_inventario.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {

        private Integer id;
    private Integer productoId;
    private String codigoBodega;
    private String ubicacion;
    private Integer cantidadDisponible;
    private Integer cantidadMinima;
    private Boolean activo;
    private LocalDateTime fechaActualizacion;
}
