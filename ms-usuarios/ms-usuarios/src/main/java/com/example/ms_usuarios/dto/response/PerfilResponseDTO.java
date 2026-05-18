package com.example.ms_usuarios.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PerfilResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer nivelAcceso;
    private boolean habilitado;
    private LocalDate fechaCreacion;
}