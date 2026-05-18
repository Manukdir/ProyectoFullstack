package com.example.ms_inventario.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockDTO {

        private Integer id;
    private Integer inventarioId;
    private String tipoMovimiento;
    private String motivo;
    private String documentoReferencia;
    private Integer cantidad;
    private BigDecimal costoMovimiento;
    private Boolean activo;
    private LocalDateTime fechaMovimiento;
}
