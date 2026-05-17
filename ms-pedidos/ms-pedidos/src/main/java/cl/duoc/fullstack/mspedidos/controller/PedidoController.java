package cl.duoc.fullstack.mspedidos.controller;

import cl.duoc.fullstack.mspedidos.dto.PedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.PedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoDTO> save(@Valid @RequestBody PedidoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(request));
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Integer id, @Valid @RequestBody PedidoRequestDTO request) {
        return ResponseEntity.ok(pedidoService.update(id, request));
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedidos/pagados")
    public ResponseEntity<List<PedidoDTO>> buscarPagadosRecientes() {
        return ResponseEntity.ok(pedidoService.buscarPagadosRecientes());
    }

}
