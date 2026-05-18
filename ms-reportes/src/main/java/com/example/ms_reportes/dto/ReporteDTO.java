package com.example.ms_reportes.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

        private Integer id;
    private String nombre;
    private String tipoReporte;
    private String resumen;
    private Integer cantidadPedidos;
    private BigDecimal totalVentas;
    private Boolean activo;
    private LocalDateTime fechaGeneracion;
}
