package com.example.ms_usuarios.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PerfilRequestDTO {

    @NotBlank(message = "El nombre del perfil es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotNull(message = "El nivel de acceso es obligatorio")
    @Min(value = 1, message = "El nivel de acceso debe ser al menos 1")
    private Integer nivelAcceso;

    private boolean habilitado = true;


    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaCreacion;

    @NotNull(message = "Debe indicar a qué usuario pertenece (ID)")
    private Integer usuarioId;
}