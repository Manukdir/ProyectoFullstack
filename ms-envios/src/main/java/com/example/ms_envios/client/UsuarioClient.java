package com.example.ms_envios.client;

import com.example.ms_envios.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para obtener la información del usuario que recibe el envío.
 */
@FeignClient(name = "MS-USUARIOS")
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Integer id);
}
