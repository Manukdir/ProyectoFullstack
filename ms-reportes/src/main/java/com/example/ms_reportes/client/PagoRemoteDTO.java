package com.example.ms_reportes.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoRemoteDTO {

    private Integer id;
    private Integer pedidoId;
    private String estadoPago;
    private BigDecimal monto;
    private Boolean pagado;
}
