package com.example.ms_sucursales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {

        private Integer id;
    private Integer regionId;
    private String nombre;
    private String direccion;
    private Integer capacidadBodega;
    private BigDecimal ventasMensuales;
    private Boolean activa;
    private LocalDate fechaApertura;
}
