package com.example.ms_pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {

        private Integer id;
    private Integer pedidoId;
    private Integer productoId;
    private String nombreProducto;
    private String observacion;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
}
