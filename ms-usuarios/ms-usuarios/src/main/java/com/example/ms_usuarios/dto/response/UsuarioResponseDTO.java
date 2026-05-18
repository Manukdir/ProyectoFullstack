package com.example.ms_usuarios.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioResponseDTO {
    private Integer id;
    private String nombreCompleto;
    private String email;
    private Integer puntosAcumulados;
    private boolean activo;
    private LocalDate fechaRegistro;
}