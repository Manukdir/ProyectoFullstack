package com.example.ms_reportes.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO {

        private Integer id;
    private String codigoSeguimiento;
    private Boolean entregado;
    private LocalDate fechaEnvio;
}
