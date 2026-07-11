package com.example.ms_usuarios.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private Boolean activo;
    private Integer perfilId;
    private String perfilNombre;
}
