package com.example.ms_reportes.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EnvioRemoteDTO {

    private Integer id;
    private Integer pedidoId;
    private String codigoSeguimiento;
    private BigDecimal costoEnvio;
    private Boolean entregado;
}
