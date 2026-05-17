package cl.duoc.fullstack.msenvios.controller;

import cl.duoc.fullstack.msenvios.dto.SeguimientoDTO;
import cl.duoc.fullstack.msenvios.dto.SeguimientoRequestDTO;
import cl.duoc.fullstack.msenvios.service.SeguimientoService;
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
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    @GetMapping("/seguimientos")
    public ResponseEntity<List<SeguimientoDTO>> findAll() {
        return ResponseEntity.ok(seguimientoService.findAll());
    }

    @GetMapping("/seguimientos/{id}")
    public ResponseEntity<SeguimientoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(seguimientoService.findById(id));
    }

    @PostMapping("/seguimientos")
    public ResponseEntity<SeguimientoDTO> save(@Valid @RequestBody SeguimientoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguimientoService.save(request));
    }

    @PutMapping("/seguimientos/{id}")
    public ResponseEntity<SeguimientoDTO> update(@PathVariable Integer id, @Valid @RequestBody SeguimientoRequestDTO request) {
        return ResponseEntity.ok(seguimientoService.update(id, request));
    }

    @DeleteMapping("/seguimientos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        seguimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
