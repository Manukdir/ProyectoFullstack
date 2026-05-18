package com.example.ms_empleados.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

        private Integer id;
    private Integer sucursalId;
    private String nombre;
    private String email;
    private String cargo;
    private Integer horasSemanales;
    private BigDecimal sueldo;
    private Boolean activo;
    private LocalDate fechaIngreso;
}
