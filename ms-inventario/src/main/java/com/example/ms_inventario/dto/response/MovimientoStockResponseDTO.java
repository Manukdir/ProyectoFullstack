package com.example.ms_inventario.dto.response;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Data
@Schema(description = "Modelo de respuesta que representa el detalle de un movimiento de stock ejecutado")
public class MovimientoStockResponseDTO {

    @Schema(description = "Identificador único del movimiento de stock", example = "10")
    private Integer id;

    @Schema(description = "Tipo de operación realizada", example = "INGRESO")
    private String tipoMovimiento;

    @Schema(description = "Detalle o justificación del movimiento registrado", example = "Compra de insumos y reabastecimiento de stock")
    private String motivo;

    @Schema(description = "Cantidad de unidades que se alteraron", example = "50")
    private Integer cantidad;

    @Schema(description = "Indica si la transacción fue aprobada en el sistema", example = "true")
    private boolean aprobado;

    @Schema(description = "Fecha exacta en que se procesó el movimiento", example = "2026-06-21")
    private LocalDate fechaMovimiento;

    @Schema(description = "ID del producto asociado al movimiento", example = "15")
    private Integer productoId;
}