package com.example.ms_pedidos.controller;

import com.example.ms_pedidos.client.ProductoClient;
import com.example.ms_pedidos.client.UsuarioClient;
import com.example.ms_pedidos.dto.ProductoDTO;
import com.example.ms_pedidos.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints de prueba para comprobar la comunicación Feign con usuarios y productos.
 */
@RestController
@RequestMapping("/api/v1/pedidos/integracion")
@Tag(name = "Integración de pedidos", description = "Comunicación Feign con usuarios y productos")
public class PedidoIntegracionController {

    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    public PedidoIntegracionController(UsuarioClient usuarioClient, ProductoClient productoClient) {
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Consultar un usuario mediante Eureka y Feign")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioClient.obtenerUsuario(id));
    }

    @GetMapping("/productos/{id}")
    @Operation(summary = "Consultar un producto mediante Eureka y Feign")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Integer id) {
        return ResponseEntity.ok(productoClient.obtenerProducto(id));
    }
}
