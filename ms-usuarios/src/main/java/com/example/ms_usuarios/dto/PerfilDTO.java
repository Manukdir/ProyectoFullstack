package com.example.ms_usuarios.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

        private Integer id;
    private Integer usuarioId;
    private String nombrePerfil;
    private String descripcion;
    private Integer nivelAcceso;
    private Boolean activo;
    private LocalDate fechaCreacion;
    private Boolean permisosEspeciales;
}
