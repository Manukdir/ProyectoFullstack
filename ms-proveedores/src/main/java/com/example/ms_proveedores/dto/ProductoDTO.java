package com.example.ms_proveedores.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ProductoDTO {

    private Integer id;
    private String nombre;
    private String codigoSku;
    private Double precio;
    private Integer stock;
    private LocalDate fechaIngreso;
    private boolean disponible;
}
