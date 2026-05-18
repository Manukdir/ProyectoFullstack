package com.example.ms_sucursales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {

        private Integer id;
    private String nombre;
    private String codigo;
    private Integer cantidadComunas;
    private BigDecimal costoDespachoBase;
    private Boolean activa;
    private LocalDate fechaCreacion;
}
