package com.example.ms_envios.controller;

import com.example.ms_envios.client.PedidoClient;
import com.example.ms_envios.client.UsuarioClient;
import com.example.ms_envios.dto.PedidoDTO;
import com.example.ms_envios.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Permite comprobar la comunicación de envíos con pedidos y usuarios.
 */
@RestController
@RequestMapping("/api/v1/envios/integracion")
@Tag(name = "Integración de envíos", description = "Comunicación Feign con pedidos y usuarios")
public class EnvioIntegracionController {

    private final PedidoClient pedidoClient;
    private final UsuarioClient usuarioClient;

    public EnvioIntegracionController(PedidoClient pedidoClient, UsuarioClient usuarioClient) {
        this.pedidoClient = pedidoClient;
        this.usuarioClient = usuarioClient;
    }

    @GetMapping("/pedidos/{id}")
    @Operation(summary = "Consultar un pedido mediante Eureka y Feign")
    public ResponseEntity<PedidoDTO> obtenerPedido(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoClient.obtenerPedido(id));
    }

    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Consultar un usuario mediante Eureka y Feign")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioClient.obtenerUsuario(id));
    }
}
