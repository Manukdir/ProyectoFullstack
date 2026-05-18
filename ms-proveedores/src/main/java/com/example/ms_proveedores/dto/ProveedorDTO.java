package com.example.ms_proveedores.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {

        private Integer id;
    private String nombre;
    private String rut;
    private String email;
    private Integer calificacion;
    private BigDecimal montoCompras;
    private Boolean activo;
    private LocalDate fechaRegistro;
}
