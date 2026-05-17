package cl.duoc.fullstack.mspedidos.client;

import cl.duoc.fullstack.mspedidos.client.dto.ProductoClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-productos", url = "${servicios.productos.url}")
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoClientDTO findById(@PathVariable("id") Integer id);
}
