package cl.duoc.fullstack.mspedidos.client;

import cl.duoc.fullstack.mspedidos.client.dto.UsuarioClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-usuarios", url = "${servicios.usuarios.url}")
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioClientDTO findById(@PathVariable("id") Integer id);
}
