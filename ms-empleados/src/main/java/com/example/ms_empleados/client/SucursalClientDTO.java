package com.example.ms_empleados.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SucursalClientDTO {

    private Integer id;
    private Boolean activa;
    private String nombre;

}
