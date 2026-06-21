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
@Schema(description = "Datos necesarios para crear o actualizar un pedido")
public class PedidoRequestDTO {

    @Schema(description = "Identificador del usuario", example = "1")
        @NotNull(message = "El id de usuario es obligatorio")
        @Positive(message = "El id de usuario debe ser positivo")
        private Integer usuarioId;
    @Schema(description = "Número único del pedido", example = "PED-1001")
    @NotBlank(message = "El campo numeroPedido es obligatorio")
        @Size(min = 2, max = 150, message = "El campo numeroPedido debe tener entre 2 y 150 caracteres")
        private String numeroPedido;
    @Schema(description = "Estado actual", example = "PENDIENTE")
    @NotBlank(message = "El campo estado es obligatorio")
        @Size(min = 2, max = 150, message = "El campo estado debe tener entre 2 y 150 caracteres")
        private String estado;
    @Schema(description = "Dirección de entrega", example = "Av. Principal 123")
    @NotBlank(message = "El campo direccionEntrega es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccionEntrega debe tener entre 2 y 150 caracteres")
        private String direccionEntrega;
    @Schema(description = "Cantidad total de productos", example = "3")
    @NotNull(message = "El campo cantidadProductos es obligatorio")
        @Min(value = 0, message = "El campo cantidadProductos no puede ser negativo")
        private Integer cantidadProductos;
    @Schema(description = "Total del pedido", example = "45990.00")
    @NotNull(message = "El campo total es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo total debe ser mayor que cero")
        private BigDecimal total;
    @Schema(description = "Indica si el pedido está pagado", example = "false")
    @NotNull(message = "El campo pagado es obligatorio")
        private Boolean pagado;
    @Schema(description = "Fecha de creación del pedido", example = "2026-06-20T10:30:00")
    @NotNull(message = "El campo fechaPedido es obligatorio")
        @PastOrPresent(message = "El campo fechaPedido no puede ser futuro")
        private LocalDateTime fechaPedido;
}
