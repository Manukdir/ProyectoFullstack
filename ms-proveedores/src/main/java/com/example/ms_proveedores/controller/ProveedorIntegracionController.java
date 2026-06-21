package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.client.ProductoClient;
import com.example.ms_proveedores.dto.ProductoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint usado para verificar la comunicación Feign con productos.
 */
@RestController
@RequestMapping("/api/v1/proveedores/integracion")
@Tag(name = "Integración de proveedores", description = "Comunicación Feign con productos")
public class ProveedorIntegracionController {

    private final ProductoClient productoClient;

    public ProveedorIntegracionController(ProductoClient productoClient) {
        this.productoClient = productoClient;
    }

    @GetMapping("/productos/{id}")
    @Operation(summary = "Consultar un producto mediante Eureka y Feign")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Integer id) {
        return ResponseEntity.ok(productoClient.obtenerProducto(id));
    }
}
