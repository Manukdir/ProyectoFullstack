package com.example.ms_pagos.client;

import com.example.ms_pagos.dto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para consultar pedidos registrados en Eureka.
 */
@FeignClient(name = "MS-PEDIDOS")
public interface PedidoClient {

    @GetMapping("/api/v1/pedidos/{id}")
    PedidoDTO obtenerPedido(@PathVariable("id") Integer id);
}
