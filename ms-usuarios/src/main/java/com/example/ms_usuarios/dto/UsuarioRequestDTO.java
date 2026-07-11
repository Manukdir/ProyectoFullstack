package com.example.ms_usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato valido")
    @Size(max = 120, message = "El email no puede superar 120 caracteres")
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 30, message = "El telefono no puede superar 30 caracteres")
    private String telefono;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

    @NotNull(message = "El perfil es obligatorio")
    private Integer perfilId;
}
