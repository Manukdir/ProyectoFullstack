package com.example.ms_inventario.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MovimientoStockResponseDTO {
    private Integer id;
    private String tipoMovimiento;
    private String motivo;
    private Integer cantidad;
    private boolean aprobado;
    private LocalDate fechaMovimiento;
    private Integer productoId;
}