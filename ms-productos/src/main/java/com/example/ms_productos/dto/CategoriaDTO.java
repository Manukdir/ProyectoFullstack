package com.example.ms_productos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

        private Integer id;
    private String nombre;
    private String descripcion;
    private Integer prioridad;
    private BigDecimal margenGanancia;
    private Boolean activa;
    private LocalDate fechaCreacion;
}
