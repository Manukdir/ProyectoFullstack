package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.client.ProductoClient;
import com.example.ms_proveedores.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/proveedores/integracion")
public class ProveedorIntegracionController {

    private final ProductoClient productoClient;

    public ProveedorIntegracionController(ProductoClient productoClient) {
        this.productoClient = productoClient;
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Integer id) {
        return ResponseEntity.ok(productoClient.obtenerProducto(id));
    }
}
