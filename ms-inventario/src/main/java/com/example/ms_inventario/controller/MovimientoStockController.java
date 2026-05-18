package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.MovimientoStockDTO;
import com.example.ms_inventario.dto.MovimientoStockRequestDTO;
import com.example.ms_inventario.service.MovimientoStockService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movimientos-stock")
public class MovimientoStockController {

    @Autowired
    private MovimientoStockService movimientoStockService;


    @GetMapping
    public ResponseEntity<List<MovimientoStockDTO>> listar() {
        return ResponseEntity.ok(movimientoStockService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStockDTO> obtenerPorId(@PathVariable Integer id) {
        MovimientoStockDTO dto = movimientoStockService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MovimientoStockDTO> crear(@Valid @RequestBody MovimientoStockRequestDTO requestDTO) {
        MovimientoStockDTO creado = movimientoStockService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoStockDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody MovimientoStockRequestDTO requestDTO) {
        MovimientoStockDTO actualizado = movimientoStockService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = movimientoStockService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
