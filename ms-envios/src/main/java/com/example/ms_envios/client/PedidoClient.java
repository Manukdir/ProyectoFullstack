package com.example.ms_envios.client;

import com.example.ms_envios.dto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-pedidos", url = "${servicios.pedidos.url}")
public interface PedidoClient {

    @GetMapping("/api/v1/pedidos/{id}")
    PedidoDTO obtenerPedido(@PathVariable("id") Integer id);
}
