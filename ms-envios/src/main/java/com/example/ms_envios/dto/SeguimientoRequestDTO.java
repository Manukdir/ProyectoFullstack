package com.example.ms_envios.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoRequestDTO {

        @NotNull(message = "El id de envio es obligatorio")
        @Positive(message = "El id de envio debe ser positivo")
        private Integer envioId;
    @NotBlank(message = "El campo estado es obligatorio")
        @Size(min = 2, max = 150, message = "El campo estado debe tener entre 2 y 150 caracteres")
        private String estado;
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @NotBlank(message = "El campo ubicacion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo ubicacion debe tener entre 2 y 150 caracteres")
        private String ubicacion;
    @NotNull(message = "El campo orden es obligatorio")
        @Min(value = 0, message = "El campo orden no puede ser negativo")
        private Integer orden;
    @NotNull(message = "El campo visibleCliente es obligatorio")
        private Boolean visibleCliente;
    @NotNull(message = "El campo fechaEvento es obligatorio")
        @PastOrPresent(message = "El campo fechaEvento no puede ser futuro")
        private LocalDateTime fechaEvento;
}
