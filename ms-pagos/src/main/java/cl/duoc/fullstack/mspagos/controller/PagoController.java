package cl.duoc.fullstack.mspagos.controller;

import cl.duoc.fullstack.mspagos.dto.PagoDTO;
import cl.duoc.fullstack.mspagos.dto.PagoRequestDTO;
import cl.duoc.fullstack.mspagos.service.PagoService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
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
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/pagos")
    public ResponseEntity<List<PagoDTO>> findAll() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/pagos/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.findById(id));
    }

    @PostMapping("/pagos")
    public ResponseEntity<PagoDTO> save(@Valid @RequestBody PagoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.save(request));
    }

    @PutMapping("/pagos/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO request) {
        return ResponseEntity.ok(pagoService.update(id, request));
    }

    @DeleteMapping("/pagos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pagos/buscar")
    public ResponseEntity<List<PagoDTO>> buscarPorMontoYEstado(@RequestParam BigDecimal monto, @RequestParam String estadoPago) {
        return ResponseEntity.ok(pagoService.buscarPorMontoYEstado(monto, estadoPago));
    }

}
