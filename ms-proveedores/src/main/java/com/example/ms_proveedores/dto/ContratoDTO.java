package com.example.ms_proveedores.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {

        private Integer id;
    private Integer proveedorId;
    private String codigoContrato;
    private String descripcion;
    private Integer duracionMeses;
    private BigDecimal montoMensual;
    private Boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
}
