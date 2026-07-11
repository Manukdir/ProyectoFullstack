package com.example.ms_usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PerfilRequestDTO {

    @NotBlank(message = "El nombre del perfil es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    private String nombre;

    @Size(max = 200, message = "La descripcion no puede superar 200 caracteres")
    private String descripcion;
}
