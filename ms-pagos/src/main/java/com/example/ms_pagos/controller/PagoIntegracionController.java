package com.example.ms_pagos.controller;

import com.example.ms_pagos.client.PedidoClient;
import com.example.ms_pagos.dto.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Prueba la comunicacion de pagos con pedidos mediante Feign.
 */
@RestController
@RequestMapping("/api/v1/pagos/integracion")
@Tag(name = "Integracion de pagos")
public class PagoIntegracionController {

    private final PedidoClient pedidoClient;

    public PagoIntegracionController(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    @GetMapping("/pedidos/{id}")
    @Operation(summary = "Consultar un pedido desde pagos")
    public ResponseEntity<PedidoDTO> obtenerPedido(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoClient.obtenerPedido(id));
    }
}
