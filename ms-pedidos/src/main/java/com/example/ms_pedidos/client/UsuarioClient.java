package com.example.ms_pedidos.client;

import com.example.ms_pedidos.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign que obtiene usuarios desde el microservicio de usuarios.
 */
@FeignClient(name = "MS-USUARIOS")
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Integer id);
}
