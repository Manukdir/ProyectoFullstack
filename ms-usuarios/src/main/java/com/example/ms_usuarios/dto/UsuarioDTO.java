package com.example.ms_usuarios.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

        private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccionEntrega;
    private Integer puntosFidelidad;
    private Boolean activo;
    private LocalDate fechaRegistro;
}
