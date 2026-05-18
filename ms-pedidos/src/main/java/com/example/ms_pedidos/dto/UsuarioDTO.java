package com.example.ms_pedidos.dto;

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
    private String direccionEntrega;
    private Boolean activo;
}
