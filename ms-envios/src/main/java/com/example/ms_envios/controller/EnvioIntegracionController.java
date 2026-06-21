package com.example.ms_envios.controller;

import com.example.ms_envios.client.PedidoClient;
import com.example.ms_envios.client.UsuarioClient;
import com.example.ms_envios.dto.PedidoDTO;
import com.example.ms_envios.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/envios/integracion")
public class EnvioIntegracionController {

    private final PedidoClient pedidoClient;
    private final UsuarioClient usuarioClient;

    public EnvioIntegracionController(PedidoClient pedidoClient, UsuarioClient usuarioClient) {
        this.pedidoClient = pedidoClient;
        this.usuarioClient = usuarioClient;
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedido(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoClient.obtenerPedido(id));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioClient.obtenerUsuario(id));
    }
}
