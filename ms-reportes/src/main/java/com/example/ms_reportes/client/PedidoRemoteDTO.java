package com.example.ms_reportes.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoRemoteDTO {

    private Integer id;
    private String numeroPedido;
    private String estado;
    private BigDecimal total;
    private Boolean pagado;
}
