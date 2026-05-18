package com.example.ms_reportes.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

        private Integer id;
    private BigDecimal total;
    private Boolean pagado;
    private String estado;
}
