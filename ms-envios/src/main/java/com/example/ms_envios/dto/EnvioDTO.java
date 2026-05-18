package com.example.ms_envios.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO {

        private Integer id;
    private Integer pedidoId;
    private Integer usuarioId;
    private String codigoSeguimiento;
    private String direccionDestino;
    private String transportista;
    private BigDecimal costoEnvio;
    private Boolean entregado;
    private LocalDate fechaEnvio;
    private LocalDate fechaEstimadaEntrega;
}
