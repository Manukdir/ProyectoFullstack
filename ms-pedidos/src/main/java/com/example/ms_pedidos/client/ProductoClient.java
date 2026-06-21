package com.example.ms_pedidos.client;

import com.example.ms_pedidos.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-productos", url = "${servicios.productos.url}")
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO obtenerProducto(@PathVariable("id") Integer id);
}
