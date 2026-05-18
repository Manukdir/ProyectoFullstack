package com.example.ms_inventario.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

        private Integer id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
}
