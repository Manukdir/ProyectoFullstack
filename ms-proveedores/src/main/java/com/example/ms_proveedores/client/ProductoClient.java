package com.example.ms_proveedores.client;

import com.example.ms_proveedores.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-PRODUCTOS")
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO obtenerProducto(@PathVariable("id") Integer id);
}
