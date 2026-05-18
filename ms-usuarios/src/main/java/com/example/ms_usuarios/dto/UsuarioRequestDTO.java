package com.example.ms_usuarios.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe tener formato de email")
        @Size(max = 120, message = "El campo email no puede superar 120 caracteres")
        private String email;
    @NotBlank(message = "El campo telefono es obligatorio")
        @Size(min = 2, max = 150, message = "El campo telefono debe tener entre 2 y 150 caracteres")
        private String telefono;
    @NotBlank(message = "El campo direccionEntrega es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccionEntrega debe tener entre 2 y 150 caracteres")
        private String direccionEntrega;
    @NotNull(message = "El campo puntosFidelidad es obligatorio")
        @Min(value = 0, message = "El campo puntosFidelidad no puede ser negativo")
        private Integer puntosFidelidad;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaRegistro es obligatorio")
        @PastOrPresent(message = "El campo fechaRegistro no puede ser futura")
        private LocalDate fechaRegistro;
}
