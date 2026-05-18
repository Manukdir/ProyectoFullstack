package com.example.ms_envios.controller;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.service.SeguimientoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seguimientos")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;


    @GetMapping
    public ResponseEntity<List<SeguimientoDTO>> listar() {
        return ResponseEntity.ok(seguimientoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoDTO> obtenerPorId(@PathVariable Integer id) {
        SeguimientoDTO dto = seguimientoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SeguimientoDTO> crear(@Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoDTO creado = seguimientoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoDTO actualizado = seguimientoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = seguimientoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
