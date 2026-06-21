package com.example.ms_pedidos.controller;

import com.example.ms_pedidos.client.ProductoClient;
import com.example.ms_pedidos.client.UsuarioClient;
import com.example.ms_pedidos.dto.ProductoDTO;
import com.example.ms_pedidos.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedidos/integracion")
public class PedidoIntegracionController {

    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    public PedidoIntegracionController(UsuarioClient usuarioClient, ProductoClient productoClient) {
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioClient.obtenerUsuario(id));
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Integer id) {
        return ResponseEntity.ok(productoClient.obtenerProducto(id));
    }
}
