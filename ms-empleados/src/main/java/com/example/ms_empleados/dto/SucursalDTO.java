package com.example.ms_empleados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {

        private Integer id;
    private String nombre;
    private Boolean activa;
}
