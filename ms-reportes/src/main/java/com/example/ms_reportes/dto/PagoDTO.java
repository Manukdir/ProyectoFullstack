package com.example.ms_reportes.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

        private Integer id;
    private BigDecimal monto;
    private String estadoPago;
    private Boolean pagado;
}
