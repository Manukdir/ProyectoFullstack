package com.example.ms_envios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

        private Integer id;
    private String nombre;
    private String direccionEntrega;
    private String email;
}
