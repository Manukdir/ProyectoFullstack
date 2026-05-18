package com.example.ms_pagos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

        private Integer id;
    private Integer pedidoId;
    private String metodoPago;
    private String estadoPago;
    private String codigoTransaccion;
    private Integer numeroCuotas;
    private BigDecimal monto;
    private Boolean pagado;
    private LocalDateTime fechaPago;
}
