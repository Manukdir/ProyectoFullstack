package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.request.MovimientoStockRequestDTO;
import com.example.ms_inventario.dto.response.MovimientoStockResponseDTO;
import com.example.ms_inventario.service.MovimientoStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos-stock")
@RequiredArgsConstructor
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @GetMapping
    public ResponseEntity<List<MovimientoStockResponseDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoStockService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStockResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(movimientoStockService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MovimientoStockResponseDTO> crearMovimiento(@Valid @RequestBody MovimientoStockRequestDTO dto) {
        return new ResponseEntity<>(movimientoStockService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoStockResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody MovimientoStockRequestDTO dto) {
        return ResponseEntity.ok(movimientoStockService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        movimientoStockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<MovimientoStockResponseDTO>> buscarPorTipo(@RequestParam String tipoMovimiento) {
        return ResponseEntity.ok(movimientoStockService.buscarPorTipoYAprobado(tipoMovimiento));
    }
}