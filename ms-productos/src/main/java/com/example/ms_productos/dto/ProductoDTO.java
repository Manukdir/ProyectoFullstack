package com.example.ms_productos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

        private Integer id;
    private Integer categoriaId;
    private String nombre;
    private String descripcion;
    private String sku;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
    private LocalDate fechaIngreso;
}
