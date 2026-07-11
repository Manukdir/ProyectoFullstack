package com.example.ms_productos.client;

import com.example.ms_productos.dto.external.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Cliente Feign utilizado por ms-productos para consultar, en ms-inventario,
 * las bodegas donde existe stock de un producto.
 *
 * name  = nombre con el que ms-inventario se registra en Eureka.
 * url   = mientras se prueba en local sin pasar por el Gateway/Eureka,
 *         se usa la URL fija definida en application.yml
 *         (servicios.inventario.url). Si se quisiera resolver vía Eureka,
 *         bastaría con cambiar el atributo url por: url = "lb://MS-INVENTARIO"
 */
@FeignClient(name = "MS-INVENTARIO")
public interface InventarioClient {

    @GetMapping("/api/v1/inventarios/producto/{productoId}")
    List<InventarioDTO> buscarPorProductoId(@PathVariable("productoId") Integer productoId);
}
