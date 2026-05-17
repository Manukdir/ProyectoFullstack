package cl.duoc.fullstack.mspedidos.controller;

import cl.duoc.fullstack.mspedidos.dto.DetallePedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.DetallePedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.service.DetallePedidoService;
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
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/detalles-pedido")
    public ResponseEntity<List<DetallePedidoDTO>> findAll() {
        return ResponseEntity.ok(detallePedidoService.findAll());
    }

    @GetMapping("/detalles-pedido/{id}")
    public ResponseEntity<DetallePedidoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(detallePedidoService.findById(id));
    }

    @PostMapping("/detalles-pedido")
    public ResponseEntity<DetallePedidoDTO> save(@Valid @RequestBody DetallePedidoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detallePedidoService.save(request));
    }

    @PutMapping("/detalles-pedido/{id}")
    public ResponseEntity<DetallePedidoDTO> update(@PathVariable Integer id, @Valid @RequestBody DetallePedidoRequestDTO request) {
        return ResponseEntity.ok(detallePedidoService.update(id, request));
    }

    @DeleteMapping("/detalles-pedido/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
