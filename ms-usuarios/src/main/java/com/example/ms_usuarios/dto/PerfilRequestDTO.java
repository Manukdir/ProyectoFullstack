package com.example.ms_usuarios.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilRequestDTO {

        @NotNull(message = "El id de usuario es obligatorio")
        @Positive(message = "El id de usuario debe ser positivo")
        private Integer usuarioId;
    @NotBlank(message = "El campo nombrePerfil es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombrePerfil debe tener entre 2 y 150 caracteres")
        private String nombrePerfil;
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @NotNull(message = "El campo nivelAcceso es obligatorio")
        @Min(value = 0, message = "El campo nivelAcceso no puede ser negativo")
        private Integer nivelAcceso;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaCreacion es obligatorio")
        @PastOrPresent(message = "El campo fechaCreacion no puede ser futura")
        private LocalDate fechaCreacion;
    @NotNull(message = "El campo permisosEspeciales es obligatorio")
        private Boolean permisosEspeciales;
}
