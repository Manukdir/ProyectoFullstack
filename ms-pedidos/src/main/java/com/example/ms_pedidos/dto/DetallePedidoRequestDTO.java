package com.example.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de un producto asociado a un pedido")
public class DetallePedidoRequestDTO {

    @Schema(description = "Identificador del pedido", example = "1")
        @NotNull(message = "El id de pedido es obligatorio")
        @Positive(message = "El id de pedido debe ser positivo")
        private Integer pedidoId;
    @Schema(description = "Identificador del producto", example = "2")
    @NotNull(message = "El id de producto es obligatorio")
        @Positive(message = "El id de producto debe ser positivo")
        private Integer productoId;
    @Schema(description = "Nombre del producto", example = "Teclado mecánico")
    @NotBlank(message = "El campo nombreProducto es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombreProducto debe tener entre 2 y 150 caracteres")
        private String nombreProducto;
    @Schema(description = "Observación del detalle", example = "Color negro")
    @NotBlank(message = "El campo observacion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo observacion debe tener entre 2 y 150 caracteres")
        private String observacion;
    @Schema(description = "Cantidad solicitada", example = "2")
    @NotNull(message = "El campo cantidad es obligatorio")
        @Min(value = 0, message = "El campo cantidad no puede ser negativo")
        private Integer cantidad;
    @Schema(description = "Precio unitario", example = "29990.00")
    @NotNull(message = "El campo precioUnitario es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo precioUnitario debe ser mayor que cero")
        private BigDecimal precioUnitario;
    @Schema(description = "Subtotal del detalle", example = "59980.00")
    @NotNull(message = "El campo subtotal es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo subtotal debe ser mayor que cero")
        private BigDecimal subtotal;
    @Schema(description = "Indica si el detalle está activo", example = "true")
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @Schema(description = "Fecha de registro", example = "2026-06-20T10:30:00")
    @NotNull(message = "El campo fechaRegistro es obligatorio")
        @PastOrPresent(message = "El campo fechaRegistro no puede ser futuro")
        private LocalDateTime fechaRegistro;
}
