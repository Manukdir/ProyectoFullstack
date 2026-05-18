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
public class PedidoRequestDTO {

        @NotNull(message = "El id de usuario es obligatorio")
        @Positive(message = "El id de usuario debe ser positivo")
        private Integer usuarioId;
    @NotBlank(message = "El campo numeroPedido es obligatorio")
        @Size(min = 2, max = 150, message = "El campo numeroPedido debe tener entre 2 y 150 caracteres")
        private String numeroPedido;
    @NotBlank(message = "El campo estado es obligatorio")
        @Size(min = 2, max = 150, message = "El campo estado debe tener entre 2 y 150 caracteres")
        private String estado;
    @NotBlank(message = "El campo direccionEntrega es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccionEntrega debe tener entre 2 y 150 caracteres")
        private String direccionEntrega;
    @NotNull(message = "El campo cantidadProductos es obligatorio")
        @Min(value = 0, message = "El campo cantidadProductos no puede ser negativo")
        private Integer cantidadProductos;
    @NotNull(message = "El campo total es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo total debe ser mayor que cero")
        private BigDecimal total;
    @NotNull(message = "El campo pagado es obligatorio")
        private Boolean pagado;
    @NotNull(message = "El campo fechaPedido es obligatorio")
        @PastOrPresent(message = "El campo fechaPedido no puede ser futuro")
        private LocalDateTime fechaPedido;
}
