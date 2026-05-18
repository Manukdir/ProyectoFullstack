package com.example.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoRequestDTO {

        @NotNull(message = "El id de pedido es obligatorio")
        @Positive(message = "El id de pedido debe ser positivo")
        private Integer pedidoId;
    @NotNull(message = "El id de producto es obligatorio")
        @Positive(message = "El id de producto debe ser positivo")
        private Integer productoId;
    @NotBlank(message = "El campo nombreProducto es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombreProducto debe tener entre 2 y 150 caracteres")
        private String nombreProducto;
    @NotBlank(message = "El campo observacion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo observacion debe tener entre 2 y 150 caracteres")
        private String observacion;
    @NotNull(message = "El campo cantidad es obligatorio")
        @Min(value = 0, message = "El campo cantidad no puede ser negativo")
        private Integer cantidad;
    @NotNull(message = "El campo precioUnitario es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo precioUnitario debe ser mayor que cero")
        private BigDecimal precioUnitario;
    @NotNull(message = "El campo subtotal es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo subtotal debe ser mayor que cero")
        private BigDecimal subtotal;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaRegistro es obligatorio")
        @PastOrPresent(message = "El campo fechaRegistro no puede ser futuro")
        private LocalDateTime fechaRegistro;
}
