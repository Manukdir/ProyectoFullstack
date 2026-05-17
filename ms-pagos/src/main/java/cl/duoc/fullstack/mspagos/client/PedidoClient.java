package cl.duoc.fullstack.mspagos.client;

import cl.duoc.fullstack.mspagos.client.dto.PedidoClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-pedidos", url = "${servicios.pedidos.url}")
public interface PedidoClient {

    @GetMapping("/api/v1/pedidos/{id}")
    PedidoClientDTO findById(@PathVariable("id") Integer id);
}
