package com.example.ms_inventario.dto.external;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String codigoSku;
    private Double precio;
    private Integer stock;
    private LocalDate fechaIngreso;
    private boolean disponible;
}