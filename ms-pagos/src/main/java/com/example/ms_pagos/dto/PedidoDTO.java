package com.example.ms_pagos.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

        private Integer id;
    private Integer usuarioId;
    private BigDecimal total;
    private Boolean pagado;
    private String estado;
}
