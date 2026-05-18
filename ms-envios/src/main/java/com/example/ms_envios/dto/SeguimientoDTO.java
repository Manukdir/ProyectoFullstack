package com.example.ms_envios.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoDTO {

        private Integer id;
    private Integer envioId;
    private String estado;
    private String descripcion;
    private String ubicacion;
    private Integer orden;
    private Boolean visibleCliente;
    private LocalDateTime fechaEvento;
}
