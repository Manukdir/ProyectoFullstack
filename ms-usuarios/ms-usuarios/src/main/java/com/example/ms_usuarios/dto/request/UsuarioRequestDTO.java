package com.example.ms_usuarios.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un formato de correo válido")
    private String email;

    @NotNull(message = "Los puntos no pueden ser nulos")
    @Min(value = 0, message = "Los puntos acumulados no pueden ser negativos")
    private Integer puntosAcumulados;


    private boolean activo = true;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaRegistro;
}