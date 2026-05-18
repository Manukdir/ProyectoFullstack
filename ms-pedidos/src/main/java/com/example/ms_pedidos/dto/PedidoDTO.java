package com.example.ms_pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

        private Integer id;
    private Integer usuarioId;
    private String numeroPedido;
    private String estado;
    private String direccionEntrega;
    private Integer cantidadProductos;
    private BigDecimal total;
    private Boolean pagado;
    private LocalDateTime fechaPedido;
}
